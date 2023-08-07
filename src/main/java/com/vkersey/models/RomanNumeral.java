package com.vkersey.models;

import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for creating Roman Numerals
 */
public class RomanNumeral {

    private static final Logger LOGGER = LogManager.getLogger(RomanNumeral.class);
    private final int decimal;
    private final String romanNumeral;

    /**
     * Mapping for different values of Roman Numeral Letters and their weight. Note must be in descending order
     */
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

    /**
     * Constructor that takes in a decimal and finds the corresponding roman numeral
     * @param decimal - int number in decimal form
     */
    public RomanNumeral(int decimal){
        // Roman numeral bounds
        this.decimal = decimal;
        if (decimal > 0 && decimal < 4000) {
            this.romanNumeral = convertDecimal(decimal);
        } else {
            this.romanNumeral = "";
        }
        LOGGER.debug("{} converted to {}", this.decimal, this.romanNumeral);
    }

    /**
     * Helper method for converting an Arabic number to a Roman Numeral
     * @param decimal - int Arabic number
     * @return - String Roman Numeral
     */
    private String convertDecimal(int decimal) {
        int current = decimal;
        StringBuilder romanNumeralBuilder = new StringBuilder();
        //Iterate over the items in the map in order
        for (Map.Entry<String, Integer> entry : romanNumeralSteps.entrySet()) {
            String letter = entry.getKey();
            int value = entry.getValue();
            //Subtract the value from the current value and add the letter to the roman numeral
            while (current >= value) {
                current -= value;
                romanNumeralBuilder.append(letter);
            }
        }
        return romanNumeralBuilder.toString();
    }

    public int getDecimal() {
        return decimal;
    }

    public String getRomanNumeral() {
        return romanNumeral;
    }

    /**
     * Overrode the toString method to return json with both the input (decimal) and the output (roman numeral)
     * @return String json
     */
    @Override
    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("input", String.valueOf(this.decimal));
        jsonObject.addProperty("output", this.romanNumeral);
        return jsonObject.toString();
    }
}
