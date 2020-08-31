package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(new Solution(4));
        Solution solution = new Solution(5);

        FileOutputStream outputStream = new FileOutputStream("D:\\Java programs\\New.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(solution);
        outputStream.close();
        objectOutputStream.close();

        FileInputStream inputStream = new FileInputStream("D:\\Java programs\\New.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object object = objectInputStream.readObject();
        Solution newSolution = (Solution) object;
        inputStream.close();
        objectInputStream.close();

        System.out.println(solution == newSolution);
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
