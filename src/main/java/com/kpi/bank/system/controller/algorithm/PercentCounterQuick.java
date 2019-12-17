package com.kpi.bank.system.controller.algorithm;

import com.kpi.bank.system.model.Client;
import com.kpi.bank.system.model.Credit;

public class PercentCounterQuick extends PercentCounter {

    @Override
    public Float depositPercent(Credit credit) {
        return (float) 10 + (credit.getPeriodDays() / 365);
    }

    @Override
    public  Float creditPercent(Credit credit) {
        return (float) 12 + (credit.getPeriodDays() / 365);
    }
}
