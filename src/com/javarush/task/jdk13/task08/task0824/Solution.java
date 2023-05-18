package com.javarush.task.jdk13.task08.task0824;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Вся семья в сборе
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<Human> children0 = new ArrayList<>();

        Human child1 = new Human("Jack", true, 12, children0);
        Human child2 = new Human("John", true, 11, children0);
        Human child3 = new Human("Joe", true, 10, children0);

        ArrayList<Human> children = new ArrayList<>(Arrays.asList(child1, child2, child3));
        Human father = new Human("John", true, 40, children);
        Human mother = new Human("Sara", false, 41, children);

        ArrayList<Human> children1 = new ArrayList<>(Arrays.asList(father));
        Human grandfather1 = new Human("John11", true, 80, children1);
        Human grandmother1 = new Human("Sara11", false, 81, children1);

        ArrayList<Human> children2 = new ArrayList<>(Arrays.asList(mother));
        Human grandfather2 = new Human("John22", true, 80, children2);
        Human grandmother2 = new Human("Sara22", false, 81, children2);

        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);

        System.out.println(father);
        System.out.println(mother);

        System.out.println(grandfather1);
        System.out.println(grandmother1);

        System.out.println(grandfather2);
        System.out.println(grandmother2);
    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}
