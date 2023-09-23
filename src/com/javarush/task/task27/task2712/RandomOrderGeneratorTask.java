package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tabletList;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tabletList = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int ind = (int) (Math.random() * tabletList.size());
                Tablet currentTablet = tabletList.get(ind);
                currentTablet.createTestOrder();

                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
