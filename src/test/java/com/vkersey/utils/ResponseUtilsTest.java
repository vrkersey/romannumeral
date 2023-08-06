package com.vkersey.utils;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class ResponseUtilsTest {

    @Test
    public void testSetHtmlResponse() throws IOException {
        String expectedResponseBody = "<span>test</span>";

        HttpExchange httpExchange = mock(HttpExchange.class);
        Headers headers = new Headers();
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);

        ResponseUtils.setHtmlResponse(httpExchange, expectedResponseBody);

        assertTrue(headers.containsKey("Content-Type"));
        assertTrue(headers.get("Content-Type").contains("text/html"));
        assertEquals(expectedResponseBody, actualResponseBody.toString());
    }

    @Test
    public void testSetJsonResponse() throws IOException {
        String expectedResponseBody = "{\"test\": true}";

        HttpExchange httpExchange = mock(HttpExchange.class);
        Headers headers = new Headers();
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);

        ResponseUtils.setJsonResponse(httpExchange, expectedResponseBody);

        assertTrue(headers.containsKey("Content-Type"));
        assertTrue(headers.get("Content-Type").contains("application/json"));
        assertEquals(expectedResponseBody, actualResponseBody.toString());
    }

    @Test
    public void testSetErrorResponse() throws IOException {
        String expectedResponseBody = "There is a bug in this code";

        HttpExchange httpExchange = mock(HttpExchange.class);
        Headers headers = new Headers();
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        when(httpExchange.getResponseHeaders()).thenReturn(headers);
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);

        ResponseUtils.setErrorResponse(httpExchange, expectedResponseBody, 400);

        assertTrue(headers.containsKey("Content-Type"));
        assertTrue(headers.get("Content-Type").contains("text/html"));
        assertTrue(actualResponseBody.toString().contains(expectedResponseBody));
    }
}
