/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4.printqueue;

/**
 * Выводит в консоль строки в заданном порядке
 */
public class PrintString implements Runnable {

    private final Controller controller;
    private final String str;
    private final int order;

    /**
     * Конструктор
     *
     * @param controller контроллер очереди, следит за порядком
     * @param str        строка, которую надо распетчатать
     * @param order      номер в очереди
     */
    public PrintString(Controller controller, String str, int order) {
        this.controller = controller;
        this.str = str;
        this.order = order;

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        int count = 5;
        for (int i = 0; i < count; i++) {
            controller.print(str, order);
        }
    }

}
