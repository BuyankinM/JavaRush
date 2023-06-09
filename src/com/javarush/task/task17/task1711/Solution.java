package com.javarush.task.task17.task1711;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 0; i < args.length / 3; i++) {
                        int idx = 1 + 3 * i;
                        String name = args[idx++];
                        String sexStr = args[idx++];
                        String dateStr = args[idx];
                        Date birthday = (new SimpleDateFormat("d/M/y", Locale.ENGLISH)).parse(dateStr);

                        if ("м".equals(sexStr)) allPeople.add(Person.createMale(name, birthday));
                        else allPeople.add(Person.createFemale(name, birthday));

                        System.out.println(allPeople.size() - 1);
                    }
                    break;
                }
            case "-u":
                synchronized (allPeople) {
                    DateFormat df = new SimpleDateFormat("d/M/y", Locale.ENGLISH);
                    for (int i = 0; i < args.length / 4; i++) {
                        int idx = 1 + 4 * i;

                        int idPerson = Integer.parseInt(args[idx++]);
                        String name = args[idx++];
                        String sexStr = args[idx++];
                        String dateStr = args[idx];
                        Date birthday = df.parse(dateStr);

                        Person person = allPeople.get(idPerson);
                        person.setName(name);
                        person.setSex(("м".equals(sexStr) ? Sex.MALE : Sex.FEMALE));
                        person.setBirthDate(birthday);
                    }
                    break;
                }
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        int idPerson = Integer.parseInt(args[i]);
                        Person person = allPeople.get(idPerson);
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);
                    }
                    break;
                }
            case "-i":
                synchronized (allPeople) {
                    DateFormat df = new SimpleDateFormat("dd-MMM-y", Locale.ENGLISH);
                    for (int i = 1; i < args.length; i++) {
                        int idPerson = Integer.parseInt(args[i]);
                        Person person = allPeople.get(idPerson);

                        String name = person.getName();
                        String sex = person.getSex() == Sex.MALE ? "м" : "ж";
                        String bd = df.format(person.getBirthDate());
                        System.out.printf("%s %s %s\n", name, sex, bd);
                    }
                    break;
                }
        }
    }
}
