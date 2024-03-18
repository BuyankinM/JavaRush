package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
        System.out.println();
        printDate("9.10.2017 5:56:45");
    }

    public static void printDate(String date) {
        boolean hasDate = date.indexOf('.') > -1;
        boolean hasTime = date.indexOf(':') > -1;
        String[] splitDate = date.split(" ");

        if (hasDate) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate dt = LocalDate.parse(splitDate[0], dateFormatter);

            System.out.println("День: " + dt.getDayOfMonth());
            System.out.println("День недели: " + dt.get(ChronoField.DAY_OF_WEEK));
            System.out.println("День месяца: " + dt.getDayOfMonth());
            System.out.println("День года: " + dt.getDayOfYear());
            System.out.println("Неделя месяца: " + dt.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
            System.out.println("Неделя года: " + dt.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
            System.out.println("Месяц: " + dt.getMonth().getValue());
            System.out.println("Год: " + dt.getYear());
        }

        if (hasTime) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m:s");
            LocalTime tm = LocalTime.parse(splitDate[splitDate.length - 1], timeFormatter);

            System.out.println("AM или PM: " + (tm.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
            System.out.println("Часы: " + tm.get(ChronoField.HOUR_OF_AMPM));
            System.out.println("Часы дня: " + tm.get(ChronoField.HOUR_OF_DAY));
            System.out.println("Минуты: " + tm.get(ChronoField.MINUTE_OF_HOUR));
            System.out.println("Секунды: " + tm.get(ChronoField.SECOND_OF_MINUTE));
        }
    }
}
