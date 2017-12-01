package thinkingJava.nio.select;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
    private static Selector selector; //通道管理器

    public static void init(String ip, int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        selector = Selector.open();
        channel.connect(new InetSocketAddress(ip, port));
        channel.register(selector, SelectionKey.OP_CONNECT);
        System.out.println("客户端程序已启动。。。");
    }

    public static void listen() throws IOException, InterruptedException {
        while (true) {
            selector.select();
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) it.next();
                it.remove();
                if (selectionKey.isConnectable()) { //连接事件发生
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap("你好Server，我是Client,我已经发起连接".getBytes()));
                    //在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    readAndWrite(selectionKey);
                }
            }
            //TimeUnit.MILLISECONDS.sleep(2000);
        }
    }

    private static void readAndWrite(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("客户端收到信息：" + msg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String content = reader.readLine();
        channel.write(ByteBuffer.wrap(content.getBytes()));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        init("localhost", 9000);
        listen();
    }
}
