package com.example.toucan.timeprovider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeProvider {

    public static String getTime() {
        Calendar now = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        return formatter.format(now.getTime());
    }
}
