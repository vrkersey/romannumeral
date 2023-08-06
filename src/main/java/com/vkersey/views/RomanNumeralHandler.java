package com.vkersey.views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vkersey.models.RomanNumeral;
import com.vkersey.utils.RequestUtils;
import com.vkersey.utils.ResponseUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Handler for requests made to the stop endpoint
 */
public class RomanNumeralHandler implements HttpHandler {

    /**
     * Takes a decimal number passed using the `query` parameter, converts it to roman numeral, and returns either;
     *  - json : with an 'input' property (the original decimal) and an 'output' property (the roman numeral)
     *  - html : error indicating that the query parameter was missing, outside the range, or not a number
     * @param httpExchange HttpExchange - used to get the query parameter and handle the response
     * @throws IOException - Thrown if there is an issue getting the query or writing the response
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // Get the query string from the request
        String query = httpExchange.getRequestURI().getQuery();
        // Get all parameters
        Map<String, String> parameters = RequestUtils.getParameters(query);
        String decimalString = parameters.getOrDefault("query", null);
        if (decimalString == null) {
            String text = "Missing 'query' parameter";
            ResponseUtils.setErrorResponse(httpExchange, text, 400);
        } else if(!canConvertToInt(decimalString)) {
            String text = "'query' parameter is not a number";
            ResponseUtils.setErrorResponse(httpExchange, text, 400);
        } else {
            int decimal = Integer.parseInt(decimalString);
            if (decimal > 0 && decimal < 4000) {
                // Number is within the range so convert it to Roman Numeral
                RomanNumeral romanNumeral = new RomanNumeral(decimal);
                ResponseUtils.setJsonResponse(httpExchange, romanNumeral.toString());
            } else {
                String text = "Integer outside the range of 1-3999";
                ResponseUtils.setErrorResponse(httpExchange, text, 400);
            }
        }
    }

    /**
     * Helper function to check if the string can safely be converted to an int
     * @param decimal - String number to check
     * @return - true iff the String is a number that can be converted
     */
    private boolean canConvertToInt (String decimal) {
        return decimal.matches("[0-9.]+");
    }
}
