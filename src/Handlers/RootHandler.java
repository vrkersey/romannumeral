package Handlers;

import Utils.ResponseUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

public class RootHandler implements HttpHandler {

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
