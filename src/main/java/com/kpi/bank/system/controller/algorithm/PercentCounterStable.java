package com.kpi.bank.system.controller.algorithm;

import com.kpi.bank.system.model.Credit;

public class PercentCounterStable extends PercentCounter {

    @Override
    public Float depositPercent(Credit credit) {
        return (float) 3 + (credit.getPeriodDays() / 365 * 10);
    }

    @Override
    public Float creditPercent(Credit credit) {
        return (float) 4 + (credit.getPeriodDays() / 365 * 20);
    }
}
