package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses;

    public static Hippodrome game;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horsesCur = new ArrayList<>();
        horsesCur.add(new Horse("Sivka", 3, 0));
        horsesCur.add(new Horse("Burka", 3, 0));
        horsesCur.add(new Horse("Blade", 3, 0));

        game = new Hippodrome(horsesCur);
        game.run();
        game.printWinner();
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) horse.print();
        for (int i = 0; i < 10; i++) System.out.println();
    }

    public Horse getWinner() {
        return Collections.max(horses);
    }

    public void printWinner() {
        Horse winner = getWinner();
        System.out.printf("Winner is %s!", winner.getName());
    }
}
