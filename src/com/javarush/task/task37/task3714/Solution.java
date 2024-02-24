package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int val;
        int prev_val = 1000;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c =='I') val = 1;
            else if (c =='V') val = 5;
            else if (c =='X') val = 10;
            else if (c =='L') val = 50;
            else if (c =='C') val = 100;
            else if (c =='D') val = 500;
            else val = 1000;

            res += val;
            if (val > prev_val) res -= 2*prev_val;
            prev_val = val;
        }
        return res;
    }
}
