/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson6;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

public class App {

    public static int[] split(int[] arr) {
        final int split = 4;
        int len = arr.length;

        for (int i = len - 1; i >= 0; i--) {
            if (arr[i] == split) {
                return Arrays.copyOfRange(arr, i + 1, len);
            }
        }

        throw new RuntimeException(split + " not found");
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
