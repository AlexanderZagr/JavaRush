package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.Arrays;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] armstrongs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477, 146511208, 472335975, 534494836, 912985153, 4679307774L, 32164049650L, 32164049651L, 40028394225L, 42678290603L, 44708635679L, 49388550606L, 82693916578L, 94204591914L, 28116440335967L, 4338281769391370L, 4338281769391371L, 21897142587612075L, 35641594208964132L, 35875699062250035L, 1517841543307505039L, 3289582984443187032L, 4498128791164624869L, 4929273885928088826L};
        ArrayList<Long> list = new ArrayList<Long>();
        for (Long aLong : armstrongs) {
            if (aLong < N) {
                list.add(aLong);
            }
        }
        long[] result = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        Long t0 = System.currentTimeMillis();
        long[] numbers = getNumbers(Integer.MAX_VALUE);
//        long[] numbers = getNumbers(146511208);
//        int[] numbers = getNumbers(100);
        Long t1 = System.currentTimeMillis();
        System.out.println("time: " + (t1 - t0) / 1000d + " sec");
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");
        System.out.println(Arrays.toString(numbers));


    }
}
