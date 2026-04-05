package lesson_11_SimpleWebServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SimpleWebServer {

    public static void main(String[] args) throws IOException {

        InetSocketAddress address = new InetSocketAddress(8080);
        HttpServer httpServer = HttpServer.create(address, 0);

        httpServer.createContext("/", (exchange) -> {
            String path = exchange.getRequestURI().getPath();

            System.out.println("[" + LocalTime.now() + "] Запрос: " + path);

            switch (path) {
                case "/hello" ->
                        sendResponse(exchange, "Привет, мир!", "text/plain;charset=UTF-8", 200);

                case "/about" -> {
                    String html = "<h1>О сервере</h1>\n<p>Это мой первый простой HTTP-сервер на Java</p>";
                    sendResponse(exchange, html, "text/html;charset=UTF-8", 200);
                }

                case "/time" -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String currentTime = "Текущее время: " + LocalTime.now().format(formatter);
                    sendResponse(exchange, currentTime, "text/plain;charset=UTF-8", 200);
                }

                default ->
                        sendResponse(exchange, "404: Страница не найдена", "text/plain;charset=UTF-8", 404);
            }
        });

        httpServer.setExecutor(null);

        System.out.println("✅ Сервер запущен. Откройте в браузере:");
        System.out.println("   http://localhost:8080/hello");
        System.out.println("   http://localhost:8080/about");
        System.out.println("   http://localhost:8080/time");

        httpServer.start();
    }

    private static void sendResponse(HttpExchange exchange,
                                     String responseText,
                                     String contentType,
                                     int statusCode) throws IOException {
        byte[] bytes = responseText.getBytes(StandardCharsets.UTF_8);

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", contentType);

        exchange.sendResponseHeaders(statusCode, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}