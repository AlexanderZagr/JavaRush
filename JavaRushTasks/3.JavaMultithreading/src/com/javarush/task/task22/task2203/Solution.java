package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        int first = string.indexOf("\t") + 1;
        char[] array = string.toCharArray();
        int last = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '\t') {
                count++;
                if (count == 1) last = array.length;
                else if (count == 2) {
                    last = i;
                    break;
                }
            }
        }
        if (count < 2) throw new TooShortStringException();
        return string.substring(first, last);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
