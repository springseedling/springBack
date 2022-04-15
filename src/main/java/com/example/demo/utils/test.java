package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args){
        String a = "[\"4月15日\",\"4月16日\"]";
        System.out.println(a);
        String b = a.replaceAll("\\]","").replaceAll("\\[","").replaceAll("\"","");
        String[] c = b.split("\\,");
        System.out.println(c[1]);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
        System.out.println(formatter.format(date));
    }
}
