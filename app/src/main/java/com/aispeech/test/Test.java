package com.aispeech.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liangjiatang on 2017/3/30.
 */

public class Test {
    public static void main(String[] args) throws ParseException {
        String s = "2017-03-30";
        String pattern = "(\\d{4})-?(\\d{2})-?(\\d{2})";
        Pattern r = Pattern.compile(pattern);
        Matcher ma = r.matcher(s);
        if (ma.find()) {
            String yyyy = ma.group(1);
            String mm = ma.group(2);
            String dd = ma.group(3);
            System.out.println(yyyy + "-" + mm + "-" + dd);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        Date date = sdf.parse(s);
    }
}
