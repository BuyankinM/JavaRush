package com.javarush.task.jdk13.task02.task0213;

/* 
У каждого животного должна быть хозяйка
*/

public class Solution {
    public static void main(String[] args) {
        Woman woman = new Woman();

        Cat cat = new Cat();
        cat.owner = woman;

        Dog dog = new Dog();
        dog.owner = woman;

        Fish fish = new Fish();
        fish.owner = woman;
    }

    public static class Cat {
        public Woman owner;
    }

    public static class Dog {
        public Woman owner;
    }

    public static class Fish {
        public Woman owner;
    }

    public static class Woman {
    }
}
