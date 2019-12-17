package com.kpi.bank.system.view;

import com.kpi.bank.system.input.console.ConsoleStream;
import com.kpi.bank.system.input.error.InputValidator;
import com.kpi.bank.system.input.error.UncorrectedKey;

public class View {

    public static void printInfoMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.err.println(message);
    }

    public void workInfo() {
        System.out.println("\t\t\tBank System\n\tDmytro Tsyliuryk IS_72\n\n");
    }

    public void startMenuMessage() {
        System.out.println("\t1. Work with User.");
        System.out.println("\t2. Work with Client.");
        System.out.println("\t3. Work with Manager.");
        System.out.println("\t4. Work with Card.");
        System.out.println("\t5. Work with Credit.");
        System.out.println("\t6. Work with Deposit.");
        System.out.println("\t7. Work with Transaction.");
        System.out.println("\t8. Work with PhoneReplenishment.");
        System.out.println("\t9. Work with CardReplenishment.");
        System.out.println("\t10. Exit");
    }

    public int readKeyFromConsole(int start, int end){
        int resultKey;
        String inputString;
        do {
            System.out.println("Write key number: ");
            try {

                inputString = ConsoleStream.stringFromConsole();
                InputValidator.validationIntNumber(inputString);

                resultKey = Integer.parseInt(inputString);
                InputValidator.validationIntKey(resultKey, start, end);
                break;

            } catch (UncorrectedKey e) {
                System.out.println("Please, try again!");
            }

        } while (true);

        this.cleanConsole();

        return resultKey;

    }

    public void queryMenuMessage(String entity) {
        System.out.println(String.format("\t1. Insert value to %s.", entity));
        System.out.println(String.format("\t2. Select all value from %s.", entity));
        System.out.println(String.format("\t3. Select value by ID from %s.", entity));
        System.out.println(String.format("\t4. Delete value from %s.", entity));
        System.out.println("\t5. Go back");
        System.out.println("\t6. Exit");
    }

    private void cleanConsole() {
        for(int i = 0; i < 1000; i++) {
            System.out.println("\b");
        }
    }
}
