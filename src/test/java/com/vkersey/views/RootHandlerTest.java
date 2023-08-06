package com.vkersey.views;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class RootHandlerTest {

    @Test
    public void testResponseBody() throws IOException {
        HttpExchange httpExchange = mock(HttpExchange.class);
        ByteArrayOutputStream actualResponseBody = new ByteArrayOutputStream();
        Headers actualHeaders = new Headers();
        when(httpExchange.getResponseBody()).thenReturn(actualResponseBody);
        when(httpExchange.getResponseHeaders()).thenReturn(actualHeaders);
        RootHandler rootHandler = new RootHandler();
        rootHandler.handle(httpExchange);
        assertEquals(RootHandler.RESPONSE, actualResponseBody.toString());
    }
}
