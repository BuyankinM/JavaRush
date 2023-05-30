package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();

        String params = url.substring(url.indexOf("?") + 1);
        String objValue = null;
        List<String> list = new ArrayList();

        for (String param : params.split("&")) {
            String[] parts = param.split("=");
            if (parts.length == 1) {
                list.add(param);
            } else {
                list.add(parts[0]);
                if ("obj".equals(parts[0])) {
                    objValue = parts[1];
                }
            }
        }
        System.out.println(String.join(" ", list));
        if (objValue != null) {
            if (objValue.matches("[0-9.]+")) {
                alert(Double.parseDouble(objValue));
            } else {
                alert(objValue);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
