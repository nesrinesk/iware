package com.i_ware.projet_cse_mobile.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by asus on 19/10/2017.
 */

public class DateParse {
    public static String currentdateToString(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateandTime = sdf.format(currentTime);
        return currentDateandTime;
    }
    public static String currentdateToString(Date date){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String currentDateandTime = sdf.format(date);
        return currentDateandTime;
    }
}
