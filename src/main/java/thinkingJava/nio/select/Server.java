package thinkingJava.nio.select;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Server {
    private static Selector selector; //通道管理器
    private static Charset charset = Charset.forName("UTF-8");

    public static void init(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端程序启动。。。");
    }

    public static void listen() throws IOException, InterruptedException {
        //轮询selector
        while (selector.select() > 0) {
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) it.next();
                it.remove();
                //客户端发起请求连接事件到来
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel sChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel cChannel = sChannel.accept(); //获取和客户端连接的通道
                    cChannel.configureBlocking(false);
                    //cChannel.write(ByteBuffer.wrap("你好Client，我是Server，我在等待你发起连接请求".getBytes()));
                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    cChannel.register(selector, SelectionKey.OP_READ);
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
        StringBuilder sb = new StringBuilder();
        while(channel.read(buffer) > 0) {
            buffer.flip();
            sb.append(charset.decode(buffer));
            System.out.println("Recv from client," + sb);
        }

        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("服务端收到信息：" + msg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String content = reader.readLine();
        channel.write(ByteBuffer.wrap(content.getBytes())); //将信息返回给客户端
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        init(9000);
        listen();
    }
}
