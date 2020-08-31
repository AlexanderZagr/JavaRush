package com.javarush.task.task03.task0307;

/* 
Привет StarCraft!
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        new Zerg().name = "a1";
        new Zerg().name = "a2";
        new Zerg().name = "a3";
        new Zerg().name = "a4";
        new Zerg().name = "a5";

        new Protoss().name = "p1";
        new Protoss().name = "p2";
        new Protoss().name = "p3";

        new Terran().name = "t1";
        new Terran().name = "t2";
        new Terran().name = "t3";
        new Terran().name = "t4";
    }

    public static class Zerg {
        public String name;
    }

    public static class Protoss {
        public String name;
    }

    public static class Terran {
        public String name;
    }
}
