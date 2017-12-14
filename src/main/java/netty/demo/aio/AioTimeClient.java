package netty.demo.aio;

public class AioTimeClient {
    public static void main(String[] args) {
        int port = 8088;
        new Thread(new AsyncTimeClientHandler("localhost", port), "AIO-Client").start();
    }
}
