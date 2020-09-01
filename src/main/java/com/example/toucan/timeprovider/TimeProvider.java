package com.example.toucan.timeprovider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeProvider {

    Calendar now = Calendar.getInstance();
    DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
    int formattedDate = Integer.parseInt(formatter.format(now.getTime()));
}
