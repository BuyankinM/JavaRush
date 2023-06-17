package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        Properties properties = new Properties();
        properties.putAll(runtimeStorage);

        PrintWriter printWriter = new PrintWriter(outputStream);
        properties.store(printWriter, "runtimeStorage");
        printWriter.close();
    }

    public static void load(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        Properties properties = new Properties();
        properties.load(br);

        for (Map.Entry entry : properties.entrySet()) {
            runtimeStorage.put((String) entry.getKey(), (String) entry.getValue());
        }
        br.close();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine())) {
            load(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }
}
