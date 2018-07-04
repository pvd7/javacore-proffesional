/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson1;

import ru.javacore.lesson1.task3.Apple;
import ru.javacore.lesson1.task3.Box;
import ru.javacore.lesson1.task3.Orange;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        PrintStream out = System.out;

        String str = "раз два три четыре пять вышел зайчик погулять";
        int firstPos = 0;
        int secondPos = 5;

        out.println("Original array : " + Arrays.toString(str.split(" ")));

        // массив
        String[] arr = str.split(" ");
        swapArray(arr, firstPos, secondPos);
        out.println("Swap array (" + firstPos + " <-> " + secondPos + "): " + Arrays.toString(arr));

        // список
        List<String> arrayList = new ArrayList<>(List.of(str.split(" ")));
        firstPos = 7;
        swapList(arrayList, firstPos, secondPos);
        out.println("Swap list (" + firstPos + " <-> " + secondPos + "): " + arrayList);

        // массив в список
        List<String> list = convertArrayToArrayList(str.split(" "));
        System.out.println("Array to ArrayList: " + list);

        out.println();

        // задача про фрукты и коробки
        out.println("Фрукты и коробки");

        float weightApple = 1.0f;
        float weightOrange = 1.5f;

        Box<Apple> appleBox1 = new Box<>("яблоки1");
        for (int i = 0; i < 15; i++) appleBox1.add(new Apple(weightApple));
        out.println(appleBox1);

        Box<Apple> appleBox2 = new Box<>("яблоки2");
        for (int i = 0; i < 30; i++) appleBox2.add(new Apple(weightApple));
        out.println(appleBox2);

        Box<Orange> orangeBox1 = new Box<>(Orange.class, "апельсины1", 10, weightOrange);
        out.println(orangeBox1);

        Box<Orange> orangeBox2 = new Box<>(Orange.class, "апельсины2", 20, weightOrange);
        out.println(orangeBox2);

        out.println();

        comparePrint(appleBox1, appleBox1);
        comparePrint(appleBox1, appleBox2);
        comparePrint(appleBox1, orangeBox1);
        comparePrint(orangeBox1, orangeBox2);

        out.println();

        // создаем новый ящик и перемещаем в него фрукты из другого ящика
        Box<Apple> appleBox3 = new Box<>("яблоки3");
        appleBox1.moveTo(appleBox3);
        out.println(appleBox1);
        out.println(appleBox3);
    }

    /**
     * Сравнивает массу ящиков фруктов и выводит в консоль результат
     *
     * @param firstBox первый ящик
     * @param secondBox второй ящик
     */
    private static void comparePrint(Box firstBox, Box secondBox) {
        System.out.println("Масса ящиков " + firstBox.getName() + " и " + secondBox.getName() + ((firstBox.compare(secondBox)) ? " равна" : " не равна"));
    }

    /**
     * Меняет местами элементы в массиве
     *
     * @param arr       массив
     * @param firstPos  позиция первого элемента
     * @param secondPos позиция вторго элемента
     * @param <T>       параметр-тип
     */
    private static <T> void swapArray(T[] arr, int firstPos, int secondPos) {
        T value = arr[firstPos];
        arr[firstPos] = arr[secondPos];
        arr[secondPos] = value;
    }

    /**
     * Меняет местами элементы в списке
     *
     * @param list      список
     * @param firstPos  позиция первого элемента
     * @param secondPos позиция вторго элемента
     * @param <T>       параметр-тип
     */
    private static <T> void swapList(List<T> list, int firstPos, int secondPos) {
        T value = list.set(firstPos, list.get(secondPos));
        list.set(secondPos, value);
    }

    /**
     * Преобразует массив в ArrayList;
     *
     * @param arr массив
     * @param <T> параметр-тип
     * @return ArrayList
     */
    private static <T> List<T> convertArrayToArrayList(T[] arr) {
        List<T> list = new ArrayList<>(arr.length);
        for (T anArr : arr) list.add(anArr);
        return list;
    }
}
