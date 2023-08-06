package com.vkersey.views;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class RomanNumeralHandlerTest {

    @Test
    public void testValidRequest() throws IOException, URISyntaxException {
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        HttpExchange httpExchange = setUpExchange(1, actualResponseBody);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertEquals("{\"input\": \"1\",\"output\": \"I\"}", actualResponseBody.toString());
    }
    @Test
    public void testNotAnInteger() throws IOException, URISyntaxException {
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        HttpExchange httpExchange = setUpExchange(1.1, actualResponseBody);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertTrue(actualResponseBody.toString().contains("ERROR"));
    }

    @Test
    public void testOutsideRangeNegative() throws IOException, URISyntaxException {
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        HttpExchange httpExchange = setUpExchange(-5, actualResponseBody);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertTrue(actualResponseBody.toString().contains("ERROR"));
    }

    @Test
    public void testOutsideRangeZero() throws IOException, URISyntaxException {
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        HttpExchange httpExchange = setUpExchange(0, actualResponseBody);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertTrue(actualResponseBody.toString().contains("ERROR"));
    }

    @Test
    public void testOutsideRangeVeryLarge() throws IOException, URISyntaxException {
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        HttpExchange httpExchange = setUpExchange(5000, actualResponseBody);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertTrue(actualResponseBody.toString().contains("ERROR"));
    }

    @Test
    public void testNotANumber() throws IOException, URISyntaxException {
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        HttpExchange httpExchange = setUpExchange("one", actualResponseBody);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertTrue(actualResponseBody.toString().contains("ERROR"));
    }

    @Test
    public void testMissingQuery() throws IOException, URISyntaxException {
        String fullurl = "http://localhost:8080/romannumeral";
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        URI uri = new URI(fullurl);
        HttpExchange httpExchange = mock(HttpExchange.class);
        Headers actualHeaders = new Headers();
        when(httpExchange.getResponseHeaders()).thenReturn(actualHeaders);
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);
        when(httpExchange.getRequestURI()).thenReturn(uri);

        RomanNumeralHandler romanNumeralHandler = new RomanNumeralHandler();
        romanNumeralHandler.handle(httpExchange);
        assertTrue(actualResponseBody.toString().contains("ERROR"));
    }

    private HttpExchange setUpExchange(Object query, OutputStream body) throws URISyntaxException {
        String fullurl = "http://localhost:8080/romannumeral?query=" + query;
        URI uri = new URI(fullurl);
        HttpExchange httpExchange = mock(HttpExchange.class);
        Headers actualHeaders = new Headers();
        when(httpExchange.getResponseHeaders()).thenReturn(actualHeaders);
        when(httpExchange.getResponseBody()).thenReturn(body);
        when(httpExchange.getRequestURI()).thenReturn(uri);
        return httpExchange;
    }
}
