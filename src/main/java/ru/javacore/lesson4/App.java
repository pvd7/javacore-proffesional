/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4;

import ru.javacore.lesson4.task1.Controller;
import ru.javacore.lesson4.task1.PrintString;

public class App {

    public static void main(String[] args) {
        Controller controller = new Controller(3);
        new PrintString(controller, "A", 1);
        new PrintString(controller, "B", 2);
        new PrintString(controller, "C", 3);
    }

}
