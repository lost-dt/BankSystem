package com.kpi.bank.system.controller;

import com.kpi.bank.system.view.View;

public class Controller {

    static private final View view = new View();

    public Controller() {

    }

    public View getView() {
        return view;
    }

    public static void printInfoMessage(String message) {
        View.printInfoMessage(message);
    }

    public static void printErrorMessage(String message) {
        View.printErrorMessage(message);
    }
}
