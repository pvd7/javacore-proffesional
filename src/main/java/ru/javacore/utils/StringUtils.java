/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.utils;

public class StringUtils {

    public static String getFirstWord(String str) {
        int i;
        return ((i = str.indexOf(" ")) == -1) ? str : str.substring(0, i);
//        int i = str.indexOf(" ");
//        if (i == -1) {
//            return str;
//        } else {
//            return str.substring(0, i);
//        }
    }

    public static boolean isEmpty(String str) {
        return (str == null) || str.equals("");
    }
}
