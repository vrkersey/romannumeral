package com.vkersey.views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.vkersey.utils.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Handler for requests made to the stop endpoint
 */
public class StopHandler implements HttpHandler {
    private static final Logger LOGGER = LogManager.getLogger(StopHandler.class);
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
     */
    @Override
    public void handle(HttpExchange httpExchange) {
        LOGGER.debug("Stop Handler hit");
        ResponseUtils.setHtmlResponse(httpExchange, RESPONSE);
        server.stop(2);
        LOGGER.info("Server Stopped");
    }
}
