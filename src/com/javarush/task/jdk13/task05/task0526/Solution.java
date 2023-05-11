package com.javarush.task.jdk13.task05.task0526;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {
        Man man1 = new Man("Jack", 50, "MOSCOW");
        Man man2 = new Man("John", 45, "SPB");
        System.out.println(man1);
        System.out.println(man2);

        Woman woman1 = new Woman("Lara", 30, "MOSCOW");
        Woman woman2 = new Woman("Sara", 25, "SPB");
        System.out.println(woman1);
        System.out.println(woman2);
    }

    public static class Man {
        private String name;
        private String address;
        private int age;

        public Man(String name, int age, String address) {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public String toString() {
            return String.format("%s %d %s", this.name, this.age, this.address);
        }
    }

    public static class Woman {
        private String name;
        private String address;
        private int age;

        public Woman(String name, int age, String address) {
            this.name = name;
            this.address = address;
            this.age = age;
        }

        public String toString() {
            return String.format("%s %d %s", this.name, this.age, this.address);
        }
    }
}
