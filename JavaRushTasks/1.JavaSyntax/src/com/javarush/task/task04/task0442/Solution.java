package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int summ = 0;

        while (true) {
            int number = Integer.parseInt(reader.readLine());
            if (number != -1) summ += number;
            else {
                summ += number;
                break;
            }
        }

        System.out.println(summ);
    }
}
