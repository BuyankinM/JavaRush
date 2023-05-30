package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        try {
            reset();
        } catch (IOException e) {

        }
    }

    public static CanFly result;

    public static void reset() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();

        if (name.equals("helicopter")) {
            result = new Helicopter();
        } else {
            int pass = Integer.parseInt(bufferedReader.readLine());
            result = new Plane(pass);
        }
    }
}
