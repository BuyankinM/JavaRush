package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> ORDER_QUEUE = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Cook cook = new Cook("Stepan");
        cook.setQueue(ORDER_QUEUE);
        new Thread(cook).start();

        Cook cook2 = new Cook("Mike");
        cook2.setQueue(ORDER_QUEUE);
        new Thread(cook2).start();

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);
        cook2.addObserver(waiter);

        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(ORDER_QUEUE);
            tablets.add(tablet);
        }

        Thread thread = new Thread(new RandomOrderGeneratorTask(
                tablets,
                ORDER_CREATING_INTERVAL));
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printArchivedVideoSet();
        directorTablet.printActiveVideoSet();
    }
}
