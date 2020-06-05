package e.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServerDemo {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8093), 0);
        server.start();
        server.createContext(
                "/logback.xml", new HttpHandler() {
                    @Override
                    public void handle(HttpExchange httpExchange) throws IOException {
                        byte[] bodyBytes = getXml(httpExchange).getBytes("UTF-8");

                        httpExchange.sendResponseHeaders(200, bodyBytes.length);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(bodyBytes);
                        }
                    }

                    private String getXml(HttpExchange httpExchange) {
                        return "lxj";
                    }
                });
    }
}
