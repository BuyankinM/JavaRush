package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread target) {
        this.thread = target;
    }

    @Override
    public void run() {
        Thread.State state = null;
        while (true) {
            Thread.State curState = thread.getState();
            if (state == null || state != curState) {
                System.out.println(curState);
                state = curState;
                if (curState == State.TERMINATED) {
                    interrupt();
                    break;
                }
            }
        }
    }
}
