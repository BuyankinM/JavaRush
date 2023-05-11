package com.javarush.task.jdk13.task05.task0511;

/* 
Заполнить класс Dog
*/

public class Dog {
    private String name;
    private int height;
    private String color;

    public void initialize(String name) {
        this.name = name;
    }

    public void initialize(String name, int age) {
        this.name = name;
        this.height = age;
    }

    public void initialize(String name, int age, String sex) {
        this.name = name;
        this.height = age;
        this.color = sex;
    }

    public static void main(String[] args) {

    }
}
