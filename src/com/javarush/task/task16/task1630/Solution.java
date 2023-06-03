package com.javarush.task.task16.task1630;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Последовательный вывод файлов
*/

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            firstFileName = bufferedReader.readLine();
            secondFileName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();

        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {
        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {

        private String fileName;
        private List<String> list = new ArrayList<>();

        @Override
        public void run() {
            String line;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                while ((line = bufferedReader.readLine()) != null) list.add(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void setFileName(String fullFileName) {
            fileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            return String.join(" ", list);
        }
    }
}
