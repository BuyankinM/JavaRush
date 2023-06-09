package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static String nameFile1, nameFile2;

    public static void main(String[] args) throws CorruptedDataException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {
            nameFile1 = bufferedReader.readLine();
            nameFile2 = bufferedReader.readLine();
        } catch (IOException e) {
        }

        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(nameFile1));
             BufferedReader bufferedReader2 = new BufferedReader(new FileReader(nameFile2));) {

            String line;
            while ((line = bufferedReader1.readLine()) != null) allLines.add(line);
            while ((line = bufferedReader2.readLine()) != null) forRemoveLines.add(line);
        } catch (IOException e) {
        }

        new Solution().joinData();
    }

    public void joinData() throws CorruptedDataException {
        int numToRemove = forRemoveLines.size();
        for (String line : forRemoveLines) {
            if (allLines.removeIf(s -> s.equals(line))) numToRemove -= 1;
        }

        if (numToRemove > 0) {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
