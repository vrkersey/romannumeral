package Utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseUtils {
    public static void setHtmlResponse(HttpExchange httpExchange, String html) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        httpExchange.getResponseHeaders().add("charset", "UTF-8");
        httpExchange.sendResponseHeaders(200, html.length());
        writeResponseBody(httpExchange, html);
    }

    public static void setJsonResponse(HttpExchange httpExchange, String json) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        httpExchange.getResponseHeaders().add("charset", "UTF-8");
        httpExchange.sendResponseHeaders(200, json.length());
        writeResponseBody(httpExchange, json);
    }

    public static void setErrorResponse(HttpExchange httpExchange, String errorText, int htmlErrorCode) throws IOException {
        String html = "<h1>Error: <i>" + errorText + "</i></h1>";
        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        httpExchange.getResponseHeaders().add("charset", "UTF-8");
        httpExchange.sendResponseHeaders(htmlErrorCode, html.length());
        writeResponseBody(httpExchange, html);
    }

    private static void writeResponseBody(HttpExchange httpExchange, String responseBody) throws IOException {
        try(OutputStream os = httpExchange.getResponseBody()){
            os.write(responseBody.getBytes());
        }
    }
}
