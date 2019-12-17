package com.kpi.bank.system.controller.algorithm;

import com.kpi.bank.system.model.Credit;

public abstract class PercentCounter {

    public abstract Float depositPercent(Credit credit);
    public abstract Float creditPercent(Credit credit);
}
