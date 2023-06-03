package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new infThread());
        threads.add(new exceptThread());
        threads.add(new uraThread());
        threads.add(new messageThread());
        threads.add(new inputThread());
    }

    public static void main(String[] args) {
    }

    public static class infThread extends Thread {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

    public static class exceptThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                    return;
                }
            }
        }
    }

    public static class uraThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class messageThread extends Thread implements Message {
        @Override
        public void run() {
            while (!isInterrupted()) {
            }
        }

        @Override
        public void showWarning() {
            interrupt();
        }
    }

    public static class inputThread extends Thread {
        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int sum = 0;

            while (true) {
                try {
                    String input = bufferedReader.readLine();
                    if ("N".equals(input)) break;
                    else sum += Integer.parseInt(input);
                } catch (IOException e) {
                }
            }
            System.out.println(sum);
        }
    }
}