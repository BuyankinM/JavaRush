package com.javarush.task.jdk13.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleMap();
        printPeopleMap(map);
    }

    public static Map<String, String> createPeopleMap() {
        Map<String, String> peoples = new HashMap<>();
        peoples.put("Ivanov", "Ivan");
        peoples.put("Ivanov", "Ivan");
        peoples.put("Ivanov", "Ivan");
        peoples.put("Ivanov", "Ivan");
        peoples.put("Ivanov", "Ivan");
        peoples.put("Ivanov1", "Ivan");
        peoples.put("Ivanov2", "Ivan");
        peoples.put("Ivanov3", "Ivan3");
        peoples.put("Ivanov4", "Ivan4");
        peoples.put("Ivanov5", "Ivan5");
        return peoples;
    }

    public static void printPeopleMap(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
