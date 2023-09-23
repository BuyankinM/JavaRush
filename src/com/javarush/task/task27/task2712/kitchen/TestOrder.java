package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestOrder extends Order {
    private final int MAX_DISHES_IN_ORDER = 4;

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        int numDishes = (int) (Math.random() * MAX_DISHES_IN_ORDER);
        int curDish;

        Dish[] allDishes = Dish.values();
        for (int i = 0; i < numDishes; i++) {
            curDish = (int) (Math.random() * allDishes.length);
            dishes.add(allDishes[curDish]);
        }
    }
}
