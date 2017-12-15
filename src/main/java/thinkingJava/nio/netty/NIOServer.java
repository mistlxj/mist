package thinkingJava.nio.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/*
* Netty中使用的Reactor模式，引入了多Reactor，也即一个主Reactor负责监控所有的连接请求，
* 多个子Reactor负责监控并处理读/写请求，减轻了主Reactor的压力，降低了主Reactor压力太大而造成的延迟。
* ****************************************************************************************************
* 每个子Reactor分别属于一个独立的线程，每个成功连接后的Channel的所有操作由同一个线程处理。
* 这样保证了同一请求的所有状态和上下文在同一个线程中，避免了不必要的上下文切换。
* */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.bind(new InetSocketAddress(1234));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        int coreNum = Runtime.getRuntime().availableProcessors() * 2;
        Processor[] processors = new Processor[coreNum];
        for (int i=0;i<coreNum;i++) {
            processors[i] = new Processor(coreNum);
        }

        int index = 0;
        while (selector.select() > 0) {
            Set<SelectionKey> selectKeys = selector.selectedKeys();
            for (SelectionKey key : selectKeys) {
                selectKeys.remove(key);
                if (key.isAcceptable()) {
                    ServerSocketChannel sChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = sChannel.accept();
                    clientChannel.configureBlocking(false);
                    Processor processor = processors[(int) (index++) % coreNum];
                    processor.addChannel(clientChannel);
                    processor.wakeUp();
                }
            }
        }
    }
}
