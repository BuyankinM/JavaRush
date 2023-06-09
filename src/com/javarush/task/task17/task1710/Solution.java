package com.javarush.task.task17.task1710;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        if ("-c".equals(args[0])) {
            // create
            String name = args[1];
            String sex = args[2];
            Date birthday = new SimpleDateFormat("d/M/y", Locale.ENGLISH).parse(args[3]);

            if ("м".equals(sex)) allPeople.add(Person.createMale(name, birthday));
            else allPeople.add(Person.createFemale(name, birthday));

            System.out.println(allPeople.size() - 1);
        } else if ("-r".equals(args[0])) {
            // read
            Person person = allPeople.get(Integer.parseInt(args[1]));
            String name = person.getName();
            String sex = (person.getSex() == Sex.MALE) ? "м" : "ж";

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String strDate = formatter.format(person.getBirthDate());
            System.out.printf("%s %s %s\n", name, sex, strDate);
        } else if ("-u".equals(args[0])) {
            // update
            int idx = Integer.parseInt(args[1]);
            String name = args[2];
            String sex = args[3];
            Date birthday = new SimpleDateFormat("d/M/y", Locale.ENGLISH).parse(args[4]);

            Person person = allPeople.get(idx);
            person.setName(name);
            person.setSex(("м".equals(sex) ? Sex.MALE : Sex.FEMALE));
            person.setBirthDate(birthday);
        } else if ("-d".equals(args[0])) {
            // delete
            Person person = allPeople.get(Integer.parseInt(args[1]));
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }
    }
}
