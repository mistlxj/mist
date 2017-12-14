package netty.demo.nio;

public class NioTimeClient {
    public static void main(String[] args) {
        int port = 8083;
        new Thread(new TimeClientHandler("localhost", port)).start();
    }
}
