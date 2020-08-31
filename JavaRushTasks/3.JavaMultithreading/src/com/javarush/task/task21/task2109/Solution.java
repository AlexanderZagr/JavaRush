package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B implements Cloneable {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new C(this.getI(), this.getJ(), this.getName());
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        C c = new C(1, 2, "L");
        B b = new B(1, 2, "L");
        A a = new A(1, 2);

        A aa = (A) a.clone();
        System.out.println(aa.getI() + " " + aa.getJ());
        C cc = (C) c.clone();
        System.out.println(cc.getI() + " " + cc.getJ() + " " + cc.getName());
        B bb = (B) b.clone();
        System.out.println(bb.getI() + " " + bb.getJ() + " " + bb.getName());
    }
}
