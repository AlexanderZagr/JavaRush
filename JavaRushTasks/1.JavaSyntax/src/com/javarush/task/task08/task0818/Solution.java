package com.javarush.task.task08.task0818;

import java.util.*;

/* 
Только для богачей
*/

public class Solution {

    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код
        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 500);
        map.put("B", 100);
        map.put("F", 5000);
        map.put("C", 50000);
        map.put("G", 5500);
        map.put("H", 5700);
        map.put("Y", 5800);
        map.put("U", 55500);
        map.put("I", 5400);
        map.put("O", 3500);

        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код
        Set<String> keysToRemove = new HashSet<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 500) keysToRemove.add(entry.getKey());
        }

        map.keySet().removeAll(keysToRemove);
    }

    public static void main(String[] args) {
       /* HashMap<String, Integer> test = createMap();
        removeItemFromMap(test);
        for(Map.Entry<String, Integer> entry: test.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }*/
    }
}