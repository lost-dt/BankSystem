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

    public void startMenu() {

        TaskManager.startMenu(this);

    }

    void printWorkInfo() {
        view.workInfo();
    }

    void printStartMenuMessage() {
        view.startMenuMessage();
    }

    public void printQueryMenuMessage(String entity) {
        view.queryMenuMessage(entity);
    }

    public int readKeyFromConsole(int start, int end) {
        return view.readKeyFromConsole(start, end);
    }
}
