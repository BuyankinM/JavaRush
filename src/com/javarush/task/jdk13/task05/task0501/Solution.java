package com.javarush.task.jdk13.task05.task0501;

/* 
Кошачья бойня(1)
*/

public class Solution {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Vasya", 2, 3, 4);
        Cat cat2 = new Cat("Mura", 3, 3, 3);
        Cat cat3 = new Cat("Barsik", 4, 3, 5);

        System.out.println(cat1.fight(cat2));
        System.out.println(cat1.fight(cat3));
        System.out.println(cat2.fight(cat3));
    }

    public static class Cat {
        private String name;
        private int age;
        private int weight;
        private int strength;

        public Cat(String name, int age, int weight, int strength) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.strength = strength;
        }

        public boolean fight(Cat anotherCat) {
            int scores = Integer.compare(this.strength, anotherCat.strength);
            scores += Integer.compare(this.weight, anotherCat.weight);
            scores += Integer.compare(this.age, anotherCat.age);
            return scores > 0;
        }
    }
}
