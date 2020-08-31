package com.javarush.task.task25.task2506;

/**
 * Created by hp on 31.07.2017.
 */
public class LoggingStateThread extends Thread {
    Thread target;

    public LoggingStateThread(Thread target) {
        this.target = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state = target.getState();
        System.out.println(state);

        while (state != State.TERMINATED) {
            if (state != target.getState()) {
                state = target.getState();
                System.out.println(target.getState());
            }
        }
    }
}
