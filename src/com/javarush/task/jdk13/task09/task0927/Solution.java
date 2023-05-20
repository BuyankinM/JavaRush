package com.javarush.task.jdk13.task09.task0927;

import java.util.*;

/* 
Десять котов
*/

public class Solution {

    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        Map<String, Cat> catsMap = new HashMap<>();
        catsMap.put("Barsik", new Cat("Barsik"));
        catsMap.put("Barsik1", new Cat("Barsik1"));
        catsMap.put("Barsik2", new Cat("Barsik2"));
        catsMap.put("Barsik3", new Cat("Barsik3"));
        catsMap.put("Barsik4", new Cat("Barsik4"));
        catsMap.put("Barsik5", new Cat("Barsik5"));
        catsMap.put("Barsik6", new Cat("Barsik6"));
        catsMap.put("Barsik7", new Cat("Barsik7"));
        catsMap.put("Barsik8", new Cat("Barsik8"));
        catsMap.put("Barsik9", new Cat("Barsik9"));
        return catsMap;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        Set<Cat> catSet = new HashSet<>();
        catSet.addAll(map.values());
        return catSet;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        public String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Cat)) {
                return false;
            }
            Cat cat = (Cat) o;
            return Objects.equals(name, cat.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
