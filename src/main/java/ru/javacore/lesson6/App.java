/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson6;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
    }

    public static int[] split(int[] arr) {
        final int split = 4;
        int splitIndex = 0;
        boolean find = false;
        int len = arr.length;

        for (int i = len - 1; i >= 0; i--) {
            if (arr[i] == split) {
                find = true;
                splitIndex = i;
                break;
            }
        }

        if (!find) throw new RuntimeException(split + " not found");

        return Arrays.copyOfRange(arr, splitIndex + 1, len);
    }

    public static boolean check(int[] arr) {
        boolean find1 = false;
        boolean find4 = false;
        for (int i : arr) {
            if ((i == 1) || (i == 4)) {
                if (!find1 && (i == 1)) {
                    find1 = true;
                } else if (!find4 && (i == 4)) {
                    find4 = true;
                }
            } else {
                return false;
            }
        }
        return find1 && find4;
    }

}
