package com.javarush.task.jdk13.task08.task0802;

import java.util.HashMap;
import java.util.Map;

/* 
Map из 10 пар
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> classificatory = new HashMap<>();
        classificatory.put("арбуз", "ягода");
        classificatory.put("банан", "трава");
        classificatory.put("вишня", "ягода");
        classificatory.put("груша", "фрукт");
        classificatory.put("дыня", "овощ");
        classificatory.put("ежевика", "куст");
        classificatory.put("жень-шень", "корень");
        classificatory.put("земляника", "ягода");
        classificatory.put("ирис", "цветок");
        classificatory.put("картофель", "клубень");

        for (Map.Entry<String, String> pair : classificatory.entrySet()) {
            System.out.printf("%s - %s\n", pair.getKey(), pair.getValue());
        }
    }
}
