/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String str = "раз два три четрые пять вышел зайчик погулять";
        int firstPos = 0;
        int secondPos = 5;

        System.out.println("Original array : " + Arrays.toString(str.split(" ")));

        // массив
        String[] arr = str.split(" ");
        swapArray(arr, firstPos, secondPos);
        System.out.println("Swap array (" + firstPos + " <-> " + secondPos + "): " + Arrays.toString(arr));

        // список
        List<String> arrayList = new ArrayList<>(List.of(str.split(" ")));
        firstPos = 7;
        swapList(arrayList, firstPos, secondPos);
        System.out.println("Swap list (" + firstPos + " <-> " + secondPos + "): " + arrayList);

        // массив в список
        List<String> list = convertArrayToArrayList(str.split(" "));
        System.out.println("Array to ArrayList: " + list);
    }

    /**
     * Меняет местами элементы в массиве
     *
     * @param arr       массив
     * @param firstPos  позиция первого элемента
     * @param secondPos позиция вторго элемента
     * @param <T>       имя параметра типа
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
     * @param <T>       имя параметра типа
     */
    private static <T> void swapList(List<T> list, int firstPos, int secondPos) {
        T value = list.set(firstPos, list.get(secondPos));
        list.set(secondPos, value);
    }

    /**
     * Преобразует массив в ArrayList;
     *
     * @param arr массив
     * @param <T> имя параметра типа
     * @return ArrayList
     */
    private static <T> List<T> convertArrayToArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }
}
