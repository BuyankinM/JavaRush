package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    @Override
    public String toString() {
        String ans = "";
        if (!dishes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Your order: [");

            boolean first = true;
            for (Dish d : dishes) {
                if (first) first = false;
                else sb.append(", ");
                sb.append(d.toString());
            }

            sb.append("] of ");
            sb.append(tablet.toString());

            sb.append(", cooking time ");
            sb.append(String.valueOf(getTotalCookingTime()));
            sb.append("min");

            ans = sb.toString();
        }
        return ans;
    }

    public int getTotalCookingTime() {
        return dishes.stream().mapToInt(Dish::getDuration).sum();
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }
}
