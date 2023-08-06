package com.vkersey.views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.vkersey.utils.ResponseUtils;
import java.io.IOException;

/**
 * Handler for requests made to the stop endpoint
 */
public class StopHandler implements HttpHandler {
    private final HttpServer server;

    protected static final String RESPONSE = "<h1>Stopping Web Server...</h1>";

    /**
     * Override the default constructor so this handler has access to the server to stop it
     * @param server HttpServer - running Web Server
     */
    public StopHandler(HttpServer server) {
        this.server = server;
    }

    /**
     * Sends a simple html text informing the user that the server is being stopped
     * issues a command to the server to stop in two seconds
     * @param httpExchange HttpExchange - used to handle the response
     * @throws IOException - Thrown if there is an issue writing the response
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ResponseUtils.setHtmlResponse(httpExchange, RESPONSE);
        server.stop(2);
    }
}
