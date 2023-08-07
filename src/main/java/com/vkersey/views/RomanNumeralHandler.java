package com.vkersey.views;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vkersey.models.RomanNumeral;
import com.vkersey.utils.RequestUtils;
import com.vkersey.utils.ResponseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Handler for requests made to the stop endpoint
 */
public class RomanNumeralHandler implements HttpHandler {
    private static final Logger LOGGER = LogManager.getLogger(RomanNumeralHandler.class);

    protected static final String ERROR_MISSING_QUERY = "Missing 'query' parameter";
    protected static final String ERROR_INVALID_NUMBER = "'query' parameter is not an integer";
    protected static final String ERROR_NUMBER_OUTSIDE_RANGE = "Integer outside the range of 1-3999";
    /**
     * Takes a decimal number passed using the `query` parameter, converts it to roman numeral, and returns either;
     *  - json : with an 'input' property (the original decimal) and an 'output' property (the roman numeral)
     *  - html : error indicating that the query parameter was missing, outside the range, or not a number
     * @param httpExchange HttpExchange - used to get the query parameter and handle the response
     */
    @Override
    public void handle(HttpExchange httpExchange) {
        LOGGER.debug("Roman Numeral Handler hit");
        // Get the query string from the request
        String query = httpExchange.getRequestURI().getQuery();
        // Get all parameters
        Map<String, String> parameters = RequestUtils.getParameters(query);
        String decimalString = parameters.getOrDefault("query", null);
        if (decimalString == null) {
            LOGGER.debug(ERROR_MISSING_QUERY);
            ResponseUtils.setErrorResponse(httpExchange, ERROR_MISSING_QUERY, 400);
        } else if(!canConvertToInt(decimalString)) {
            LOGGER.debug(ERROR_INVALID_NUMBER);
            ResponseUtils.setErrorResponse(httpExchange, ERROR_INVALID_NUMBER, 400);
        } else {
            int decimal = Integer.parseInt(decimalString);
            if (decimal > 0 && decimal < 4000) {
                // Number is within the range so convert it to Roman Numeral
                RomanNumeral romanNumeral = new RomanNumeral(decimal);
                ResponseUtils.setJsonResponse(httpExchange, romanNumeral.toString());
            } else {
                LOGGER.debug(ERROR_NUMBER_OUTSIDE_RANGE);
                ResponseUtils.setErrorResponse(httpExchange, ERROR_NUMBER_OUTSIDE_RANGE, 400);
            }
        }
    }

    /**
     * Helper function to check if the string can safely be converted to an int
     * @param decimal - String number to check
     * @return - true iff the String is a number that can be converted
     */
    private boolean canConvertToInt (String decimal) {
        return decimal.matches("[0-9]+");
    }
}
