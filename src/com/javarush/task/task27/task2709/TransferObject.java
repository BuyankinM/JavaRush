package com.javarush.task.task27.task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() throws InterruptedException {
        while (!isValuePresent) wait();
        System.out.println("Got: " + value);

        this.isValuePresent = false;
        notifyAll();

        return value;
    }

    public synchronized void put(int value) throws InterruptedException {
        while (isValuePresent) wait();

        this.value = value;
        this.isValuePresent = true;
        notifyAll();

        System.out.println("Put: " + value);
    }
}
