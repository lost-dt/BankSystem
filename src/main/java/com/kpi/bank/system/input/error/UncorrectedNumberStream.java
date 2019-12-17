package com.kpi.bank.system.input.error;


import com.kpi.bank.system.view.View;

public class UncorrectedNumberStream extends java.lang.RuntimeException {

    UncorrectedNumberStream(String message) {
        View.printErrorMessage(message);
    }

}