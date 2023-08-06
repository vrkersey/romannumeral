package com.vkersey.utils;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ExtendWith({MockitoExtension.class})
public class RequestUtilsTest {

    @Test
    public void testGetParametersNone() {
        assertEquals(Collections.emptyMap(), RequestUtils.getParameters(""));
    }
    @Test
    public void testGetParametersOne() {
        assertEquals(Collections.singletonMap("query", "5"), RequestUtils.getParameters("query=5"));
    }
    @Test
    public void testGetParametersMany() {
        Map<String, String> actualParameters = RequestUtils.getParameters("query=15&min=1&max=100");
        assertTrue(actualParameters.containsKey("query"));
        assertEquals("15", actualParameters.get("query"));
        assertTrue(actualParameters.containsKey("min"));
        assertEquals("1", actualParameters.get("min"));
        assertTrue(actualParameters.containsKey("max"));
        assertEquals("100", actualParameters.get("max"));
    }
}
