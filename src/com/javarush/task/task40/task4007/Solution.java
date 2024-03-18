package com.javarush.task.task40.task4007;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();

        printDate("21.4.2014");
        System.out.println();

        printDate("17:33:40");
        System.out.println();

        printDate("17.3.2024 15:56:45");
    }

    public static void printDate(String date) {
        boolean hasDate = date.indexOf('.') > -1;
        boolean hasTime = date.indexOf(':') > -1;

        Date dt = new Date();
        try {
            if (hasDate && hasTime) dt = new SimpleDateFormat("d.M.yyyy h:m:s").parse(date);
            else if (hasDate) dt = new SimpleDateFormat("d.M.yyyy").parse(date);
            else dt = new SimpleDateFormat("h:m:s").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar instance = Calendar.getInstance();
        instance.setTime(dt);

        if (hasDate) {
            int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK);

            System.out.println("День: " + instance.get(Calendar.DAY_OF_MONTH));
            System.out.println("День недели: " + (dayOfWeek == 1 ? 7 : dayOfWeek - 1));
            System.out.println("День месяца: " + instance.get(Calendar.DAY_OF_MONTH));
            System.out.println("День года: " + instance.get(Calendar.DAY_OF_YEAR));
            System.out.println("Неделя месяца: " + instance.get(Calendar.WEEK_OF_MONTH));
            System.out.println("Неделя года: " + instance.get(Calendar.WEEK_OF_YEAR));
            System.out.println("Месяц: " + (instance.get(Calendar.MONTH) + 1));
            System.out.println("Год: " + instance.get(Calendar.YEAR));
        }
        if (hasTime) {
            System.out.println("AM или PM: " + (instance.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
            System.out.println("Часы: " + instance.get(Calendar.HOUR));
            System.out.println("Часы дня: " + instance.get(Calendar.HOUR_OF_DAY));
            System.out.println("Минуты: " + instance.get(Calendar.MINUTE));
            System.out.println("Секунды: " + instance.get(Calendar.SECOND));
        }
    }
}
