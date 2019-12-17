package com.kpi.bank.system.input.console;


import com.kpi.bank.system.input.error.InputValidator;
import com.kpi.bank.system.input.error.UncorrectedKey;
import com.kpi.bank.system.input.error.UncorrectedNumberStream;

import java.util.Scanner;

public class ConsoleStream {

    public static String stringFromConsole() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public static int intFromConsole() {

        Scanner scanner = new Scanner(System.in);

        boolean correct;
        String inputString = "";
        do {
            try {

                inputString = scanner.nextLine();
                InputValidator.validationIntNumber(inputString);
                correct = true;

            } catch (UncorrectedKey e) {
                correct = false;
            }
        } while (!correct);

        return Integer.parseInt(inputString);
    }

    public static float floatFromConsole() {

        Scanner scanner = new Scanner(System.in);

        boolean correct;
        String inputString = "";
        do {
            try {

                inputString = scanner.nextLine();
                InputValidator.validationFloatNumber(inputString);
                correct = true;

            } catch (UncorrectedKey e) {
                correct = false;
            }
        } while (!correct);

        return Float.parseFloat(inputString);
    }



}