package com.javarush.task.jdk13.task07.task0724;

/* 
Семья
*/

public class Solution {
    public static void main(String[] args) {
        Human grandfather = new Human("Mike-1", true, 70);
        Human grandmother = new Human("Sara-1", false, 72);

        Human grandfather_2 = new Human("Mike-2", true, 71);
        Human grandmother_2 = new Human("Sara-2", false, 73);

        Human father = new Human("John", true, 40, grandfather, grandmother);
        Human mother = new Human("Lora", false, 41, grandfather_2, grandmother_2);

        Human son_1 = new Human("John", true, 10, father, mother);
        Human son_2 = new Human("John", true, 11, father, mother);
        Human daughter = new Human("Leila", false, 9, father, mother);

        System.out.println(grandfather);
        System.out.println(grandfather_2);

        System.out.println(grandmother);
        System.out.println(grandmother_2);

        System.out.println(father);
        System.out.println(mother);

        System.out.println(son_1);
        System.out.println(son_2);
        System.out.println(daughter);
    }

    public static class Human {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;

        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public Human(String name, boolean sex, int age, Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null) {
                text += ", отец: " + this.father.name;
            }

            if (this.mother != null) {
                text += ", мать: " + this.mother.name;
            }

            return text;
        }
    }
}