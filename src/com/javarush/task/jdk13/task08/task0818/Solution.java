package com.javarush.task.jdk13.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Налоговая и зарплаты
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        Map<String, Integer> map = new HashMap<>();

        map.put("Иванов1", 10_000);
        map.put("Иванов2", 20_000);
        map.put("Иванов3", 10_000);
        map.put("Иванов4", 30_000);
        map.put("Иванов5", 40_000);
        map.put("Иванов6", 100);
        map.put("Иванов7", 200);
        map.put("Иванов8", 300);
        map.put("Иванов9", 400);
        map.put("Иванов10", 500);

        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        Map<String, Integer> map2 = new HashMap<>(map);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            if (entry.getValue() < 500) {
                map.remove(entry.getKey());
            }
        }
    }

    public static void main(String[] args) {

    }
}