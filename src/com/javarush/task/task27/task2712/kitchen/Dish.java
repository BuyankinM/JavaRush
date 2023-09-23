package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    FISH(25),
    STEAK(30),
    SOUP(15),
    JUICE(5),
    WATER(3);

    private int duration;

    Dish(int i) {
        this.duration = i;
    }

    public static String allDishesToString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Dish dish : Dish.values()) {
            if (!first) sb.append(", ");
            else first = false;
            sb.append(dish.toString());
        }
        return sb.toString();
    }

    public int getDuration() {
        return duration;
    }
}
