/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson1.task3;

import java.util.ArrayList;
import java.util.List;

/**
 * Ящик с фруктами
 *
 * @param <T> параметр-тип
 */
public class Box<T extends Fruit> {
    private final List<T> list = new ArrayList<>();
    private final String name;

    /**
     * Создает ящик и наполняет его фруктами
     *
     * @param name имя ящика
     */
    public Box(String name) {
        this.name = name;
    }

    /**
     * Создает ящик и наполняет его фруктами
     *
     * @param c      класс параметр-типа
     * @param name   имя ящика
     * @param count  количество фруктов
     * @param weight вес одного фрукта
     * @throws IllegalAccessException исключение
     * @throws InstantiationException исключение
     */
    public Box(Class<T> c, String name, int count, float weight) throws IllegalAccessException, InstantiationException {
        this.name = name;
        T t;
        for (int i = 0; i < count; i++) {
            t = c.newInstance();
            t.setWeight(weight);
            list.add(t);
        }
    }

    public List<T> getList() {
        return list;
    }

    public void add(T value) {
        list.add(value);
    }

    /**
     * Подсчитывает массу ящика
     *
     * @return масса ящика
     */
    public float getWeight() {
        float sum = 0.0f;
        for (Fruit fruit : list) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    public int getCount() {
        return list.size();
    }

    /**
     * Сравнивает массу данного ящика с другим ящиком
     *
     * @param another другой ящик
     * @return true - масса равно, false - масса не равна
     */
    public boolean compare(Box<?> another) {
        return this.getWeight() == another.getWeight();
    }

    /**
     * Переспает фрукты из данного ящика в другой
     *
     * @param another другой ящик
     * @return другой яшик
     */
    public void moveTo(Box<T> another) {
        for (T fruit : list) {
            another.add(fruit);
        }
        list.clear();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Box{" +
                "name='" + name + '\'' +
                ", count='" + getCount() + '\'' +
                ", weight='" + getWeight() + '\'' +
                '}';
    }
}
