package com.lyt.common.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtils {

    public static Date correctTime(Date date){

        String formatDate =null;
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
        formatDate = dFormat.format(date);

        SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date strD = lsdStrFormat.parse(formatDate);
            return strD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date(String.valueOf(correctTime(date)));
    }
}
