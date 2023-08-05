package Utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Utility for setting data to the response
 */
public class ResponseUtils {
    /**
     * Sets HTML to the response body and response code to 200
     * @param httpExchange - HttpExchange to set the response data to
     * @param html - String html to set to the response
     * @throws IOException - Throws if unable to modify response headers or response body
     */
    public static void setHtmlResponse(HttpExchange httpExchange, String html) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        httpExchange.getResponseHeaders().add("charset", "UTF-8");
        httpExchange.sendResponseHeaders(200, html.length());
        writeResponseBody(httpExchange, html);
    }

    /**
     * Sets Json to the response body and response code to 200
     * @param httpExchange - HttpExchange to set the response data to
     * @param json - String json to set to the response
     * @throws IOException - Throws if unable to modify response headers or response body
     */
    public static void setJsonResponse(HttpExchange httpExchange, String json) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        httpExchange.getResponseHeaders().add("charset", "UTF-8");
        httpExchange.sendResponseHeaders(200, json.length());
        writeResponseBody(httpExchange, json);
    }

    /**
     * Sets an error message to the response body sets the response code to the error code defined
     * @param httpExchange - HttpExchange to set the response data to
     * @param errorText - String text to be converted to html
     * @param htmlErrorCode - int error code to use in the response
     * @throws IOException - Throws if unable to modify response headers or response body
     */
    public static void setErrorResponse(HttpExchange httpExchange, String errorText, int htmlErrorCode) throws IOException {
        String html = "<h1>Error: <i>" + errorText + "</i></h1>";
        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        httpExchange.getResponseHeaders().add("charset", "UTF-8");
        httpExchange.sendResponseHeaders(htmlErrorCode, html.length());
        writeResponseBody(httpExchange, html);
    }

    /**
     * Helper function to write a string to the response body
     * @param httpExchange - HttpExchange to set the response data to
     * @param responseBody - String text to add to response body
     * @throws IOException - Throws if unable to modify the response body
     */
    private static void writeResponseBody(HttpExchange httpExchange, String responseBody) throws IOException {
        try(OutputStream os = httpExchange.getResponseBody()){
            os.write(responseBody.getBytes());
        }
    }
}
