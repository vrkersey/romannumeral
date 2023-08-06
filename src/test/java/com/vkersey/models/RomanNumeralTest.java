package com.vkersey.models;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith({MockitoExtension.class})
public class RomanNumeralTest {

    @Test
    public void testSimple() {
        assertEquals("I", new RomanNumeral(1).getRomanNumeral());
        assertEquals("II", new RomanNumeral(2).getRomanNumeral());
        assertEquals("IV", new RomanNumeral(4).getRomanNumeral());
        assertEquals("V", new RomanNumeral(5).getRomanNumeral());
        assertEquals("IX", new RomanNumeral(9).getRomanNumeral());
        assertEquals("X", new RomanNumeral(10).getRomanNumeral());
        assertEquals("XI", new RomanNumeral(11).getRomanNumeral());
        assertEquals("XXX", new RomanNumeral(30).getRomanNumeral());
        assertEquals("XL", new RomanNumeral(40).getRomanNumeral());
        assertEquals("L", new RomanNumeral(50).getRomanNumeral());
        assertEquals("LXXX", new RomanNumeral(80).getRomanNumeral());
        assertEquals("XC", new RomanNumeral(90).getRomanNumeral());
        assertEquals("C", new RomanNumeral(100).getRomanNumeral());
        assertEquals("CCC", new RomanNumeral(300).getRomanNumeral());
        assertEquals("CD", new RomanNumeral(400).getRomanNumeral());
        assertEquals("D", new RomanNumeral(500).getRomanNumeral());
        assertEquals("DCCC", new RomanNumeral(800).getRomanNumeral());
        assertEquals("CM", new RomanNumeral(900).getRomanNumeral());
        assertEquals("M", new RomanNumeral(1000).getRomanNumeral());
        assertEquals("MI", new RomanNumeral(1001).getRomanNumeral());
    }
    @Test
    public void testRandom() {
        assertEquals("CCXLVII", new RomanNumeral(247).getRomanNumeral());
        assertEquals("CDXLII", new RomanNumeral(442).getRomanNumeral());
        assertEquals("MMMDCCCXX", new RomanNumeral(3820).getRomanNumeral());
        assertEquals("MMDCLX", new RomanNumeral(2660).getRomanNumeral());
        assertEquals("CX", new RomanNumeral(110).getRomanNumeral());
        assertEquals("MDCCCXCIX", new RomanNumeral(1899).getRomanNumeral());
        assertEquals("MMMXCVIII", new RomanNumeral(3098).getRomanNumeral());
        assertEquals("MMDCLXXXVII", new RomanNumeral(2687).getRomanNumeral());
        assertEquals("MMMDCCCLXXXVIII", new RomanNumeral(3888).getRomanNumeral());
    }
    @Test
    public void testDecimal() {
        assertEquals(247, new RomanNumeral(247).getDecimal());
        assertEquals(442, new RomanNumeral(442).getDecimal());
        assertEquals(3820, new RomanNumeral(3820).getDecimal());
        assertEquals(2660, new RomanNumeral(2660).getDecimal());
        assertEquals(110, new RomanNumeral(110).getDecimal());
        assertEquals(1899, new RomanNumeral(1899).getDecimal());
        assertEquals(3098, new RomanNumeral(3098).getDecimal());
        assertEquals(2687, new RomanNumeral(2687).getDecimal());
        assertEquals(3888, new RomanNumeral(3888).getDecimal());
    }
    @Test
    public void testBounds() {
        assertEquals("", new RomanNumeral(0).getRomanNumeral());
        assertEquals("", new RomanNumeral(-50).getRomanNumeral());
        assertEquals("", new RomanNumeral(5543).getRomanNumeral());
    }
    @Test
    public void testToString() {
        RomanNumeral romanNumeral = new RomanNumeral(1);
        assertEquals("{\"input\": \"1\",\"output\": \"I\"}", romanNumeral.toString());
    }
}
