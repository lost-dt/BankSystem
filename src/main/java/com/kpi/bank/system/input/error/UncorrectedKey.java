package com.kpi.bank.system.input.error;

import com.kpi.bank.system.view.View;

public class UncorrectedKey extends java.lang.RuntimeException {

    UncorrectedKey(String message) {
        View.printErrorMessage(message);

    }
}