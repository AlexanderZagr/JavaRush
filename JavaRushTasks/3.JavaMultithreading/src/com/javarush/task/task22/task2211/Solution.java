package com.javarush.task.task22.task2211;

import java.io.*;
import java.util.Scanner;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        FileOutputStream outputStream = new FileOutputStream(args[1]);
        byte[] array = new byte[inputStream.available()];

        inputStream.read(array);
        outputStream.write(new String(array, "UTF-8").getBytes("Windows-1251"));

        inputStream.close();
        outputStream.close();
    }
}
