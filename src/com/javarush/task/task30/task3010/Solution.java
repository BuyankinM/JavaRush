package com.javarush.task.task30.task3010;

import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try {
            String num = args[0].toLowerCase();
            if (!num.matches("[0-9a-z]+")) {
                System.out.println("incorrect");
                return;
            }

            int result;
            int max_sym = num.chars().max().orElse(-1);

            if (max_sym > 'a') result = 10 + (max_sym - 'a') + 1;
            else if (max_sym <= '1') result = 2;
            else result = (max_sym - '0' + 1);

            System.out.println(result);
        } catch (Exception e) {
            
        }
    }
}