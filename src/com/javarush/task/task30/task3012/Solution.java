package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(1234);
    }

    public void createExpression(int number) {
        // convert to triple
        StringBuilder res3 = new StringBuilder(Integer.toString(number, 3));

        // convert to balance triple
        int l = res3.length();
        for (int i = 0; i < l; i++) {
            int idx = l - i - 1;
            int ch = res3.charAt(idx);
            if (ch < '2') continue;

            if (ch == '2') res3.setCharAt(idx, 'Z');
            else res3.setCharAt(idx, '0');

            if (idx > 0) res3.setCharAt(idx - 1, (char) (res3.charAt(idx - 1) + 1));
            else res3.insert(0, '1');
        }
        res3.reverse();

        System.out.printf("%d =", number);

        int n3 = 1;
        for (int i = 0; i < res3.length(); i++) {
            int ch = res3.charAt(i);
            if (ch == 'Z') System.out.printf(" - %d", n3);
            else if (ch == '1') System.out.printf(" + %d", n3);
            n3 *= 3;
        }
    }
}