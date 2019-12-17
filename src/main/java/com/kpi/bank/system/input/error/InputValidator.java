package com.kpi.bank.system.input.error;

public class InputValidator {


    public static void validationFloatNumber(String stringToParse){

        String pattern = "[0-9.]*$";

        if(!stringToParse.matches(pattern)) {

            throw new UncorrectedNumberStream(String.format("Your string (%s) is not number!\n", stringToParse));

        }

    }

    public static void validationIntNumber(String stringToParse) {

        String pattern = "[0-9]*$";

        if((!stringToParse.matches(pattern)) ||  (stringToParse.length() == 0)) {
            throw new UncorrectedKey(String.format("Your string (%s) is not correct to key!\n", stringToParse));
        }
    }

    public static void validationIntKey(int value, int start, int end){

        if(start > value || end < value) {
            throw new UncorrectedKey(String.format("Your key (%d) not in range [%d-%d]", value, start, end));
        }
    }
}