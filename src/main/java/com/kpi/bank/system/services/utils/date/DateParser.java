package com.kpi.bank.system.services.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    private static final SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSSS");

    public static Date parseByDefaultFormat(String dateStringPresentation) throws ParseException {
        return defaultFormat.parse(dateStringPresentation);
    }
}
