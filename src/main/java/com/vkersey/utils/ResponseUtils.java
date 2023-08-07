package com.vkersey.utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility for setting data to the response
 */
public class ResponseUtils {
    private static final Logger LOGGER = LogManager.getLogger(ResponseUtils.class);

    /**
     * Sets HTML to the response body and response code to 200
     * @param httpExchange - HttpExchange to set the response data to
     * @param html - String html to set to the response
     */
    public static void setHtmlResponse(HttpExchange httpExchange, String html) {
        writeHeader(httpExchange, "text/html", 200, html.length());
        writeResponseBody(httpExchange, html);
    }

    /**
     * Sets Json to the response body and response code to 200
     * @param httpExchange - HttpExchange to set the response data to
     * @param json - String json to set to the response
     */
    public static void setJsonResponse(HttpExchange httpExchange, String json) {
        writeHeader(httpExchange, "application/json", 200, json.length());
        writeResponseBody(httpExchange, json);
    }

    /**
     * Sets an error message to the response body sets the response code to the error code defined
     * @param httpExchange - HttpExchange to set the response data to
     * @param errorText - String text to be converted to html
     * @param htmlErrorCode - int error code to use in the response
     */
    public static void setErrorResponse(HttpExchange httpExchange, String errorText, int htmlErrorCode) {
        String html = "<h1>ERROR: <i>" + errorText + "</i></h1>";
        writeHeader(httpExchange, "text/html", htmlErrorCode, html.length());
        writeResponseBody(httpExchange, html);
    }

    /**
     * Helper function to write the required data to the response header
     * @param httpExchange - HttpExchange to set the response data to
     * @param contentType - String Content-Type
     * @param code - int HTTP response status code
     * @param contentLength - int length of content in response body
     */
    private static void writeHeader(HttpExchange httpExchange, String contentType, int code, int contentLength) {
        try {
            httpExchange.getResponseHeaders().add("Content-Type", contentType);
            httpExchange.getResponseHeaders().add("charset", "UTF-8");
            httpExchange.sendResponseHeaders(code, contentLength);
        } catch (IOException e) {
            LOGGER.error("Unable to write to the response header", e);
        }
    }

    /**
     * Helper function to write a string to the response body
     * @param httpExchange - HttpExchange to set the response data to
     * @param responseBody - String text to add to response body
     */
    private static void writeResponseBody(HttpExchange httpExchange, String responseBody) {
        try(OutputStream os = httpExchange.getResponseBody()){
            os.write(responseBody.getBytes());
        } catch (IOException e) {
            LOGGER.error("Unable to write to the response body", e);
        }
    }
}
