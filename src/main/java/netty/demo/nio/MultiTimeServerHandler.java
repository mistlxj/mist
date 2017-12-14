package netty.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultiTimeServerHandler implements Runnable {

    private Selector selector;
    private ServerSocketChannel serverChannel;
    private volatile boolean stop;

    public MultiTimeServerHandler(int port) {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The Server is start at port:" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectKeySet = selector.selectedKeys();
                Iterator<SelectionKey> it = selectKeySet.iterator();
                SelectionKey selectKey = null;
                while (it.hasNext()) {
                    selectKey = it.next();
                    it.remove();
                    try {
                        handleInput(selectKey);
                    } catch (Exception e) {
                        if (selectKey != null) {
                            selectKey.cancel();
                            if (selectKey.channel() != null) {
                                selectKey.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                SocketChannel clientChannel = serverChannel.accept();
                clientChannel.configureBlocking(false);
                clientChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = channel.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Server received from client:" + body);
                    String res = "mist".equals(body) ? "hello pig" : "omg cat";
                    doWrite(channel, res);
                } else if (readBytes < 0) {
                    key.cancel();
                    channel.close();
                } else { //读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String res) throws IOException {
        if (res != null && res.trim().length() > 0) {
            byte[] bytes = res.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
