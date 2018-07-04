/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson1.task3;

abstract class Fruit {
    private float weight;

    Fruit() {
    }

    Fruit(float weight) {
        this.weight = weight;
    }

    float getWeight() {
        return weight;
    }

    void setWeight(float weight) {
        this.weight = weight;
    }
}
