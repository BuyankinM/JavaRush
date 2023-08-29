package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    private static int priority = Thread.MIN_PRIORITY;

    {
        this.setPriority(priority++);
        if (priority > Thread.MAX_PRIORITY) priority = Thread.MIN_PRIORITY;
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }
}
