package com.vkersey;

import com.sun.net.httpserver.HttpServer;
import com.vkersey.views.RomanNumeralHandler;
import com.vkersey.views.RootHandler;
import com.vkersey.views.StopHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Java application for spinning up a webserver on port 8080
 * The Web Server has three different endpoints
 *  - /romannumeral converts a decimal number (passed using the 'query' parameter) to a roman numeral number
 *  - /stop stops the running Web Server
 *  - / (and all other requests) simple html for accessing both other endpoints
 */
public class App {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    private static final int PORT = 8080;
    private static final String DEFAULT_ENDPOINT = "/";
    private static final String STOP_ENDPOINT = "/stop";
    private static final String ROMAN_NUMERAL_ENDPOINT = "/romannumeral";

    public static void main( String[] args ) {
        try {
            //create server
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            //set endpoints
            server.createContext(DEFAULT_ENDPOINT, new RootHandler());
            server.createContext(STOP_ENDPOINT, new StopHandler(server));
            server.createContext(ROMAN_NUMERAL_ENDPOINT, new RomanNumeralHandler());
            //use default implementation of executor
            server.setExecutor(null);
            LOGGER.info("Server Started");
            //start the server
            server.start();
        } catch (IOException e) {
            LOGGER.error("Unable to create HttpServer", e);
        }
    }
}
