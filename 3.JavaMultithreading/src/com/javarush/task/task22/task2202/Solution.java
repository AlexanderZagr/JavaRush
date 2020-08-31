package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        int first = string.indexOf(" ") + 1;
        char[] array = string.toCharArray();
        int last = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ' ') {
                count++;
                if (count == 4) last = array.length;
                else if (count == 5) {
                    last = i;
                    break;
                }
            }
        }
        if (count < 4) throw new TooShortStringException();

        return string.substring(first, last);
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
