package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private String name;
        private String lastName;
        private String profession;
        private int age;
        private int weight;
        private boolean married;

        public Human(String name, int age, int weight, boolean married) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.married = married;
        }

        public Human(String name, String lastName, String profession, int age, int weight) {
            this.name = name;
            this.lastName = lastName;
            this.profession = profession;
            this.age = age;
            this.weight = weight;
        }

        public Human(String name, boolean married) {
            this.name = name;
            this.married = married;
        }

        public Human(String name, String lastName, int age) {
            this.name = name;
            this.lastName = lastName;
            this.age = age;
        }

        public Human(String name, String lastName, String profession) {
            this.name = name;
            this.lastName = lastName;
            this.profession = profession;
        }

        public Human(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }

        public Human(String name, String lastName, String profession, boolean married) {
            this.name = name;
            this.lastName = lastName;
            this.profession = profession;
            this.married = married;
        }

        public Human(String name, int age, int weight) {
            this.name = name;
            this.age = age;
            this.weight = weight;
        }

        public Human(String name, String lastName, String profession, int age, boolean married) {
            this.name = name;
            this.lastName = lastName;
            this.profession = profession;
            this.age = age;
            this.married = married;
        }

        public Human(String name, String lastName, String profession, int age, int weight, boolean married) {
            this.name = name;
            this.lastName = lastName;
            this.profession = profession;
            this.age = age;
            this.weight = weight;
            this.married = married;
        }
    }
}
