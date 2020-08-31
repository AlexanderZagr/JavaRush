package com.javarush.task.task22.task2207;

import sun.nio.cs.UTF_32;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        BufferedReader readerF = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder sb = new StringBuilder();
        while (readerF.ready()) {
            sb.append(readerF.readLine() + " ");
        }
        readerF.close();
        String[] array = sb.toString().trim().split("\\s+");
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (set.contains(i)) continue;
            for (int j = i + 1; j < array.length; j++) {
                String x = new StringBuilder(array[j]).reverse().toString();
                if (x.equals(array[i])) {
                    Pair pair = new Pair();
                    pair.first = x;
                    pair.second = array[j];
                    result.add(pair);
                    set.add(j);
                    break;
                }
            }
        }

        for (Pair pair : result) {
            System.out.println(pair);
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null && second != null ? second :
                            second == null && first != null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}