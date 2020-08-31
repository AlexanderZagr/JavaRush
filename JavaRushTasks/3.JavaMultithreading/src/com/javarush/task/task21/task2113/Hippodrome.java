package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 08.07.2017.
 */
public class Hippodrome {
    static Hippodrome game;

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse("Adel", 3, 0));
        horseList.add(new Horse("Bekki", 3, 0));
        horseList.add(new Horse("Candy", 3, 0));
        game = new Hippodrome(horseList);

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
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).move();
        }
    }

    public void print() {
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).print();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (int i = 0; i < horses.size(); i++) {
            if (winner.distance < horses.get(i).distance)
                winner = horses.get(i);
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().name + "!");
    }
}
