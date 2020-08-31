package com.javarush.task.task35.task3512;

public class Generator<T> {

    Class<T> field;

    public Generator(Class<T> field) {
        this.field = field;
    }

    T newInstance() throws IllegalAccessException, InstantiationException {
        return field.newInstance();
    }
}
