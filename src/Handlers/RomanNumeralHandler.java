package Handlers;

import Models.RomanNumeral;
import Utils.RequestUtils;
import Utils.ResponseUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RomanNumeralHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String query = httpExchange.getRequestURI().getQuery();
        int decimal = getDecimalValue(query);
        if (decimal > 0 && decimal < 4000) {
            RomanNumeral romanNumeral = new RomanNumeral(decimal);
            ResponseUtils.setJsonResponse(httpExchange, romanNumeral.toString());
        } else {
            String text = "Unable to parse integer from query parameter or integer outside the range of 1-3999";
            ResponseUtils.setErrorResponse(httpExchange, text, 400);
        }
    }

    private int getDecimalValue(String fullQuery) {
        Map<String, String> queryParameters = RequestUtils.getParameters(fullQuery);
        return queryParameters.entrySet().stream()
                .filter(entry -> entry.getKey().equals("query"))
                .map(Map.Entry::getValue)
                .map(Integer::parseInt)
                .findAny().orElse(0);
    }
}
