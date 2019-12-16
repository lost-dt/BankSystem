package com.kpi.bank.system.services.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormator {

    private static final SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSSS");

    public static String formatByDefaultFormator(Date date) {
        return defaultFormat.format(date);
    }

    public static String formatByCustomFormator(Date date, String format) {
        SimpleDateFormat dateFormator = new SimpleDateFormat(format);

        return dateFormator.format(date);
    }
}
