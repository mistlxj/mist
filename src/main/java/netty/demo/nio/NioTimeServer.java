package netty.demo.nio;

public class NioTimeServer {

    public static void main(String[] args) {
        int port = 8083;
        MultiTimeServerHandler timeServerHandler = new MultiTimeServerHandler(port);
        new Thread(timeServerHandler, "NIO-Thread-Server").start();
    }
}
