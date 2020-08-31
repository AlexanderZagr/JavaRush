package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by hp on 12.06.2018.
 */
public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        return Arrays.toString(Dish.values()).substring(1, Arrays.toString(Dish.values()).length() - 1);
    }
}
