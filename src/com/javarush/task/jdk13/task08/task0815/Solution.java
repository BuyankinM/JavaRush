package com.javarush.task.jdk13.task08.task0815;

import java.util.HashMap;
import java.util.Map;

/* 
Одинаковые имя и фамилия
*/

public class Solution {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>(10);
        map.put("Иванов", "Петр");
        map.put("Петров", "Иван");
        map.put("Сидоров", "Иван");
        map.put("Буданов", "Михаил");
        map.put("Будановский", "Сидор");
        map.put("Пановский", "Сергей");
        map.put("Поповский", "Тихон");
        map.put("Бобров", "Сеня");
        map.put("Бобровский", "Сергей");
        map.put("Раминский", "Саша");
        return map;
    }

    public static int getCountTheSameFirstName(Map<String, String> map, String name) {
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (pair.getValue() == name) {
                count++;
            }
        }
        return count;
    }

    public static int getCountTheSameLastName(Map<String, String> map, String lastName) {
        int count = 0;
        for (Map.Entry<String, String> pair : map.entrySet()) {
            if (pair.getKey() == lastName) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {

    }
}
