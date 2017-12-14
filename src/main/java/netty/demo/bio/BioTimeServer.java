package netty.demo.bio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BioTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket();
        try {
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("服务端程序已经启动。。。");
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("final 关闭服务端");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

    private static class TimeServerHandler implements Runnable {
        private Socket socket;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out = null;
            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(this.socket.getOutputStream(), true);
                String body = new String();
                while ((body = in.readLine()) != null) {
                    System.out.println("Server received: " + body);
                    out.println(body.equals("mist") ? "hello pig" : "omg cat");
                }
            } catch (IOException e) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    in = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (this.socket != null) {
                    try {
                        this.socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    this.socket = null;
                }
            }
        }
    }
}
