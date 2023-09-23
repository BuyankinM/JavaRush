package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        writeMessage("Please choose your dishes:");
        for (Dish dish : Dish.values()) {
            writeMessage(dish.toString());
        }

        List<Dish> dishes = new ArrayList<>();
        while (true) {
            String nameOfDish = readString();
            if (nameOfDish.equals("exit")) break;

            try {
                Dish dish = Dish.valueOf(nameOfDish);
                dishes.add(dish);
            } catch (IllegalArgumentException e) {
                writeMessage("Wrong dish! Try again!");
            }
        }

        return dishes;
    }
}
