/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.utils;

import java.util.Scanner;

public class StringUtils {

    public static String getFirstWord(String str, String split) {
        if (!isEmpty(str)) {
            int i;
            if ((i = str.indexOf(split)) != -1) {
                return str.substring(0, i);
            }
        }
        return str;
    }

    public static String getFirstWord(String str) {
        return getFirstWord(str, " ");
    }

    public static boolean isEmpty(String str) {
        return (str == null) || str.equals("");
    }

}
