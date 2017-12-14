package netty.demo.aio;

public class AioTimeServer {
    public static void main(String[] args) {
        int port = 8088;
        AsyncTimeServerHandler timeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(timeServerHandler, "AIO-TimeServer").start();
    }
}
