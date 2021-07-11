package com.duynt.ncovid.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
    /**
     * Get now date
     *
     * @return now date type string
     */
    public static String getNowDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
        return dateFormat.format(date);
    }

    public static String getNowTime() {
        Date dateTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(Constant.TIME_FORMAT);
        return dateFormat.format(dateTime);
    }

    public static String nullToBlank(String arg) {
        if (arg == null) {
            return "";
        }
        return arg;
    }
}
