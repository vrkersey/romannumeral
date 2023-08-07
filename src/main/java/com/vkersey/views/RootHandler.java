package com.vkersey.views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vkersey.utils.ResponseUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Handler for requests made to the root endpoint or all undefined endpoints
 */
public class RootHandler implements HttpHandler {
    private static final Logger LOGGER = LogManager.getLogger(RootHandler.class);

    protected static final String RESPONSE =
            "<h1>Roman Numeral Converter</h1>" +
            "<form action=\"/romannumeral\">" +
                "<label for=\"romannumeral\">Decimal Number to Convert:</label>" +
                "<input type=\"number\" id=\"romannumeral\" name=\"query\" min=\"1\" max=\"3999\"><br>" +
                "<input type=\"submit\" value=\"Convert\">" +
            "</form>" +
            "<form action=\"/stop\">" +
                "<input type=\"submit\" value=\"Stop Web Server\">" +
            "</form>";

    /**
     * Displays a simple form for converting a decimal number to a roman numeral using the roman numeral endpoint
     * and displays a simple form for stopping the webserver
     * @param httpExchange HttpExchange - used to handle the response
     */
    @Override
    public void handle(HttpExchange httpExchange) {
        LOGGER.debug("Root Handler hit");
        ResponseUtils.setHtmlResponse(httpExchange, RESPONSE);
    }
}
