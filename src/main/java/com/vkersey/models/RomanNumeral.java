package com.vkersey.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeral {
    private final int decimal;
    private final String romanNumeral;

    private final Map<String, Integer> romanNumeralSteps = new LinkedHashMap<String, Integer>() {{
        put("M", 1000);
        put("CM", 900);
        put("D", 500);
        put("CD", 400);
        put("C", 100);
        put("XC", 90);
        put("L", 50);
        put("XL", 40);
        put("X", 10);
        put("IX", 9);
        put("V", 5);
        put("IV", 4);
        put("I", 1);
    }};

    public RomanNumeral(int decimal){
        int current = decimal;
        StringBuilder romanNumberalBuilder = new StringBuilder();

        for (Map.Entry<String, Integer> entry : romanNumeralSteps.entrySet()) {
            String letter = entry.getKey();
            int value = entry.getValue();
            while (current >= value) {
                current -= value;
                romanNumberalBuilder.append(letter);
            }
        }
        this.decimal = decimal;
        this.romanNumeral = romanNumberalBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder output =  new StringBuilder();
        output.append("{");
        output.append("\"input\": \"");
        output.append(decimal);
        output.append("\",");
        output.append("\"output\": \"");
        output.append(romanNumeral);
        output.append("\"");
        output.append("}");
        return output.toString();
    }
}
