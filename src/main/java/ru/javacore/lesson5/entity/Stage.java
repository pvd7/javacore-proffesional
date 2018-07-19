/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson5.entity;

public abstract class Stage {

    int length;
    String description;

    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

