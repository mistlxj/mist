package thinkingJava.nio.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiPortEcho {

    private int[] ports;
    private ByteBuffer bb = ByteBuffer.allocate(1024);

    public MultiPortEcho(int[] ports) throws IOException {
        this.ports = ports;
        action();
    }

    private void action() throws IOException {
        //1  创建select
        Selector selector = Selector.open();
        //2  开启通道，并注册到selector
        for (int i=0;i<ports.length;i++) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ServerSocket ss = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            ss.bind(address); //开启服务端socket bind操作
            SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start listen on port:" + ports[i]);
        }

        //3 消费已经注册的事件，同时可以继续注册其他感兴趣的事件
        while (true) {
            //int num = selector.select(); //阻塞方法
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    //4 注册读取事件的channel
                    SelectionKey readKey = sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                    System.out.println("Get Connection from client" + sc);
                } else if((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    int bytesEcho = 0;
                    while (true) {
                        bb.clear();
                        int read = sc.read(bb);
                        if (read <= 0) {
                            break;
                        }
                        bb.flip();
                        sc.write(bb);
                        bytesEcho += read;
                    }
                    System.out.println("Echo" + bytesEcho + "from" + sc);
                    it.remove();
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        new MultiPortEcho(new int[]{9001,9002,9003});
    }
}
