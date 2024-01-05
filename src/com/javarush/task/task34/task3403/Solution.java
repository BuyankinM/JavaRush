package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution {
    public void recurse(int n) {
        if (n == 1) {
            System.out.println();
            return;
        };

        if (n % 2 == 0) {
            System.out.print("2 ");
            recurse(n / 2);
        } else {
            for (int i = 3; i <= n; i += 2) {
                if (n % i == 0) {
                    System.out.printf("%d ", i);
                    recurse(n / i);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse(18);
        solution.recurse(132);
    }
}
