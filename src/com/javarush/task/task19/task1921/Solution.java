package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        try (BufferedReader bf = new BufferedReader(new FileReader(args[0]))) {
            while (bf.ready()) {
                String[] parts = bf.readLine().split(" ");
                int l = parts.length;

                String year = parts[l - 1];
                String month = parts[l - 2];
                String day = parts[l - 3];
                SimpleDateFormat dateFormat = new SimpleDateFormat("d M y");
                Date birthDate = dateFormat.parse(String.format("%s %s %s", day, month, year));

                String name = "";
                for (int i = 0; i < l - 3; i++) name += parts[i] + " ";

                PEOPLE.add(new Person(name.trim(), birthDate));
            }
        }
    }
}
