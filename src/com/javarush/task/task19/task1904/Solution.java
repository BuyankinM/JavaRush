package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String personLine = fileScanner.nextLine();
            String[] parts = personLine.split(" ");

            Date birthDate = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-y");
            try {
                birthDate = simpleDateFormat.parse(String.format("%s-%s-%s", parts[3], parts[4], parts[5]));
            } catch (ParseException e) {
            }

            return new Person(parts[1], parts[2], parts[0], birthDate);
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
