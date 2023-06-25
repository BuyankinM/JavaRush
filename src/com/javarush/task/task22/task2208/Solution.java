package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                if (!first) sb.append(" and ");
                else first = false;

                sb.append(String.format("%s = '%s'", entry.getKey(), entry.getValue()));
            }
        }
        return sb.toString();
    }
}
