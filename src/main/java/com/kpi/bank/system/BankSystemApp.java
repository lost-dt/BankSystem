package com.kpi.bank.system;

import com.kpi.bank.system.controller.Controller;

public class BankSystemApp {

    public static void main(final String[] argv) {

        Controller controller = new Controller();

        controller.startMenu();

    }
}
