/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4;

import ru.javacore.lesson4.printqueue.Controller;
import ru.javacore.lesson4.printqueue.PrintString;

public class App {

    public static void main(String[] args) {
        // 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок
        //– ABСABСABС). Используйте wait/notify/notifyAll.
        Controller controller = new Controller(3);
        new PrintString(controller, "A", 1);
        new PrintString(controller, "B", 2);
        new PrintString(controller, "C", 3);
    }


}
