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

    public static String formatDate(String date) throws ArrayIndexOutOfBoundsException {
        if (date.contains("-")) {
            return date;
        }
        String[] arrayDate = date.split("/");
        return arrayDate[2] + "-" + arrayDate[1] + "-" + arrayDate[0];
    }

    public static String formatTime(String time) {
        return time + ":00";
    }

    public static String nullToBlank(String arg) {
        if (arg == null) {
            return "";
        }
        return arg;
    }
}
