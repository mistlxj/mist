package thinkingJava.nio.netty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Processor {
    private static int coreNum;
    private Selector selector;
    private static final ExecutorService service = Executors.newFixedThreadPool(coreNum);

    public Processor(int coreNum) throws IOException {
        this.coreNum = coreNum;
        this.selector = SelectorProvider.provider().openSelector();
        start();
    }

    private void start() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (selector.select(500) <= 0) {
                            continue;
                        }

                        Set<SelectionKey> keySet = selector.selectedKeys();
                        Iterator<SelectionKey> it = keySet.iterator();
                        while (it.hasNext()) {
                            SelectionKey key = it.next();
                            it.remove();
                            if (key.isReadable()) {
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                int cnt = socketChannel.read(buffer);
                                if (cnt < 0) {
                                    socketChannel.close();
                                    key.cancel();
                                    continue;
                                } else if (cnt == 0) {
                                    continue;
                                } else {
                                    //接收到客户端传来的数据
                                    String req = new String(buffer.array());
                                    System.out.println("Recieved from Client" + req);
                                }
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    public void addChannel(SocketChannel socketChannel) throws ClosedChannelException {
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void wakeUp() {
        this.selector.wakeup();
    }
}
