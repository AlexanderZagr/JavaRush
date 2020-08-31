package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String name;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.name = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        stream = new FileOutputStream(name, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution solutionO = new Solution("D:\\Java programs\\New.txt");
        solutionO.writeObject("Hey!");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\Java programs\\JavaProg.txt"));
        objectOutputStream.writeObject(solutionO);
        objectOutputStream.flush();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\Java programs\\JavaProg.txt"));
        Solution solution = (Solution) objectInputStream.readObject();
        solution.writeObject("Cool!");
    }
}
