package Handlers;

import Utils.ResponseUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handler for requests made to the root endpoint or all undefined endpoints
 */
public class RootHandler implements HttpHandler {

    /**
     * Displays a simple form for converting a decimal number to a roman numeral using the roman numeral endpoint
     * and displays a simple form for stopping the webserver
     * @param httpExchange HttpExchange - used to handle the response
     * @throws IOException - Thrown if there is an issue writing the response
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response =
                "<h1>Roman Numeral Converter</h1>" +
                "<form action=\"/romannumeral\">" +
                    "<label for=\"romannumeral\">Decimal Number to Convert:</label>" +
                    "<input type=\"number\" id=\"romannumeral\" name=\"query\" min=\"1\" max=\"3999\"><br>" +
                    "<input type=\"submit\" value=\"Convert\">" +
                "</form>" +
                "<form action=\"/stop\">" +
                    "<input type=\"submit\" value=\"Stop Web Server\">" +
                "</form>";

        ResponseUtils.setHtmlResponse(httpExchange, response);
    }
}
