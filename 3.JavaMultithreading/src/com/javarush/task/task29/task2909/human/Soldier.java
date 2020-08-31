package com.javarush.task.task29.task2909.human;

/**
 * Created by hp on 19.08.2017.
 */
public class Soldier extends Human {
    protected boolean isSoldier;

    public Soldier(String name, int age) {
        super(name, age);
    }

    public boolean isSoldier() {
        return isSoldier;
    }

    public void setSoldier(boolean soldier) {
        isSoldier = soldier;
    }

    public void live() {
        fight();
    }

    public void fight() {
    }
}
