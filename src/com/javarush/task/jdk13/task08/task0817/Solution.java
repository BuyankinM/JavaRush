package com.javarush.task.jdk13.task08.task0817;

import java.util.*;

/* 
Удалить людей, имеющих одинаковые имена
*/

public class Solution {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Иванов", "Иван");
        map.put("Иванов1", "Иван");
        map.put("Иванов2", "Иван");
        map.put("Иванов3", "Иван");
        map.put("Иванов4", "Петр");
        map.put("Иванов5", "Петр");
        map.put("Иванов6", "Сергей");
        map.put("Иванов7", "Александр");
        map.put("Иванов8", "Александр");
        map.put("Иванов9", "Михаил");
        return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        Map<String, Integer> counter = new HashMap<>();
        for (Map.Entry<String, String> pair : map.entrySet()) {
            String name = pair.getValue();
            counter.put(name, counter.getOrDefault(name, 0) + 1);
        }

        for (Map.Entry<String, Integer> pair : counter.entrySet()) {
            if (pair.getValue() > 1) {
                removeItemFromMapByValue(map, pair.getKey());
            }
        }
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void main(String[] args) {

    }
}
