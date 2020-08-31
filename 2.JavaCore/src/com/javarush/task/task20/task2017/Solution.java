package com.javarush.task.task20.task2017;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) throws IOException, ClassNotFoundException {
        Object object = null;
        try {
            object = objectStream.readObject();
        } catch (Exception e) {
            return null;
        }

        if (object instanceof B) return (B) object;
        else if (object instanceof A) return (A) object;
        else return null;
    }


    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {

    }
}
