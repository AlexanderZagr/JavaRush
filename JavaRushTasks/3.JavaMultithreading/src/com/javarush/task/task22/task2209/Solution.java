package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        BufferedReader readerF = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder builder = new StringBuilder();
        while (readerF.ready()) {
            builder.append(readerF.readLine() + " ");
        }
        readerF.close();
        String[] array = builder.toString().trim().split(" ");

        StringBuilder result = getLine(array);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder builder = new StringBuilder();
        if (words == null || words.length == 0) return builder;
        if (words.length == 1) return builder.append(words[0]);

        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(words));

        while (isYes(list)) {
            Collections.shuffle(list);
        }

        for (String word : list) {
            builder.append(word).append(" ");
        }

        if (builder.length() > 0) builder.replace(builder.length() - 1, builder.length(), "");
        return builder;
    }

    public static boolean isYes(ArrayList<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            String first = list.get(i);
            String last = list.get(i + 1);
            if (first.toLowerCase().charAt(first.length() - 1) != last.toLowerCase().charAt(0)) return true;
        }

        return false;
    }
}
