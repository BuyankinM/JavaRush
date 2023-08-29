package com.javarush.task.task28.task2807;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с ThreadPoolExecutor
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>();
        for (int i = 1; i <= 10; i++) {
            int localId = i;
            runnables.add(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(localId);
                }
            });
        }

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1000,
                TimeUnit.MILLISECONDS,
                runnables);

        threadPoolExecutor.prestartAllCoreThreads();
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
