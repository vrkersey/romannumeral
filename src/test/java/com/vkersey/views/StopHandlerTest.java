package com.vkersey.views;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class StopHandlerTest {

    @Test
    public void testServerStopped() throws IOException {
        HttpExchange httpExchange = mock(HttpExchange.class);
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        Headers actualHeaders = new Headers();
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);
        when(httpExchange.getResponseHeaders()).thenReturn(actualHeaders);
        HttpServer httpServer = mock(HttpServer.class);
        StopHandler stopHandler = new StopHandler(httpServer);
        stopHandler.handle(httpExchange);
        verify(httpServer, times(1)).stop(anyInt());
    }
    @Test
    public void testResponseBody() throws IOException {
        HttpExchange httpExchange = mock(HttpExchange.class);
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        Headers actualHeaders = new Headers();
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);
        when(httpExchange.getResponseHeaders()).thenReturn(actualHeaders);
        StopHandler stopHandler = new StopHandler(mock(HttpServer.class));
        stopHandler.handle(httpExchange);
        assertEquals(StopHandler.RESPONSE, actualResponseBody.toString());
    }
}
