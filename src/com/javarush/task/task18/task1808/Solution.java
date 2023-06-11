package com.javarush.task.task18.task1808;

import java.io.*;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FileInputStream fis = new FileInputStream(br.readLine());
            FileOutputStream fos1 = new FileOutputStream(br.readLine());
            FileOutputStream fos2 = new FileOutputStream(br.readLine());

            int l = fis.available();
            int mid = (l + 1) / 2;

            byte[] bytes = new byte[l];
            fis.read(bytes);

            fos1.write(bytes, 0, mid);
            fos2.write(bytes, mid, l - mid);

            fis.close();
            fos1.close();
            fos2.close();
        } catch (IOException e) {
        }
    }
}
