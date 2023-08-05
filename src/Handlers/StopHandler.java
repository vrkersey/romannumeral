package Handlers;

import Utils.ResponseUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;

public class StopHandler implements HttpHandler {
    private final HttpServer server;

    public StopHandler(HttpServer server) {
        this.server = server;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "<h1>Stopping Web Server...</h1>";
        ResponseUtils.setHtmlResponse(httpExchange, response);
        server.stop(2);
    }
}
