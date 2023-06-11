package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = null;
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        while (true) {
            String name = bufferedReader.readLine();
            if ("end".equals(name)) break;

            int idx = name.lastIndexOf(".part");
            if (nameFile == null) nameFile = name.substring(0, idx);

            int numPart = Integer.parseInt(name.substring(idx).replaceAll(".part", ""));
            treeMap.put(numPart, name);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            FileInputStream fileInputStream = new FileInputStream(entry.getValue());
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            fileOutputStream.write(buffer);
            fileInputStream.close();
        }
        fileOutputStream.close();
    }
}
