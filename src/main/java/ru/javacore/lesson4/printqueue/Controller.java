/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4.printqueue;

/**
 * Контролирует порядок вывода текста из потоков в консоль
 */
public class Controller {

    // длинна очереди
    private final int length;
    // чья очередь подошла
    private int expectOrder = 1;

    /**
     * Коструктор
     *
     * @param length длина очереди
     */
    public Controller(int length) {
        this.length = length;
    }

    /**
     * Выводит строку в консоль
     *
     * @param str   строка, которую надо вывести в консоль
     * @param order порядок в очереди
     */
    synchronized void print(String str, int order) {
        while (expectOrder != order) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(str);
        // если дошли до конца очереди, то начинаем с начала, иначе переходим к следующему
        expectOrder = (order == length) ? 1 : expectOrder + 1;

        notifyAll();
    }

}
