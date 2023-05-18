package com.javarush.task.jdk13.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Удалить всех людей, родившихся летом
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH);

        Map<String, Date> map = new HashMap<>();
        map.put("Сталлоне", dateFormat.parse("MAY 1 2012"));
        map.put("Иванов", dateFormat.parse("JUNE 1 2012"));
        map.put("Петров", dateFormat.parse("JULY 1 2012"));
        map.put("Петровский", dateFormat.parse("AUGUST 1 2012"));
        map.put("Пуловский", dateFormat.parse("MARCH 1 2012"));
        map.put("Сталлоне1", dateFormat.parse("MAY 1 2012"));
        map.put("Иванов1", dateFormat.parse("JUNE 1 2012"));
        map.put("Петров1", dateFormat.parse("JULY 1 2012"));
        map.put("Петровский1", dateFormat.parse("AUGUST 1 2012"));
        map.put("Пуловский1", dateFormat.parse("MARCH 1 2012"));

        return map;
    }

    public static void removeAllSummerPeople(Map<String, Date> map) {
        ArrayList<String> fioToDel = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();

        for (Map.Entry<String, Date> entry : map.entrySet()) {
            calendar.setTime(entry.getValue());
            int month = calendar.get(Calendar.MONTH);

            if (month > 4 && month < 8) {
                fioToDel.add(entry.getKey());
            }
        }
        for (String fio : fioToDel) {
            map.remove(fio);
        }
    }

    public static void main(String[] args) {

    }
}
