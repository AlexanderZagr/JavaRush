package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        for (Thread current : threads) {
            switch (current.getState()) {
                case NEW:
                    current.start();
                    break;
                case WAITING:
                    current.interrupt();
                    break;
                case TIMED_WAITING:
                    current.interrupt();
                    break;
                case BLOCKED:
                    current.interrupt();
                    break;
                case RUNNABLE:
                    current.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(current.getPriority());
                    break;
            }

            //implement this method - реализуйте этот метод
        }
    }

    public static void main(String[] args) {
    }
}
