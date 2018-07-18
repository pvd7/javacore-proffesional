/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4.mfd;

import java.util.concurrent.atomic.AtomicInteger;

public class MultifunctionDevice {

    public static void main(String[] args) {
        MultifunctionDevice mfd = new MultifunctionDevice();
        mfd.print(10);
        mfd.print(5);
        mfd.scan(15);
    }

    private final AtomicInteger printPageCount = new AtomicInteger(0);
    private final AtomicInteger scanPageCount = new AtomicInteger(0);

    public MultifunctionDevice() {
        // принтер
        Thread printer = new Thread(this::doSameWork, "printer");
        printer.start();

        // сканер
        Thread scanner = new Thread(this::doSameWork, "scanner");
        scanner.start();
    }

    private void doSameWork() {
        try {
            Thread.sleep(500);
            String str = "Unknown";
            int count = 0;
            switch (Thread.currentThread().getName()) {
                case "printer":
                    str = "Отпечатано";
                    count = printPageCount.get();
                    break;
                case "scanner":
                    str = "Отсканировано";
                    count = scanPageCount.get();
                    break;
            }

            while (true) {
                for (int i = 1; i <= count; i++) {
                    System.out.printf(str + " %d страниц\n", i);
                    Thread.sleep(500);
                }
                count = 0;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print(int pageCount) {
        if (printPageCount.get() == 0) {
            printPageCount.set(pageCount);
        } else {
            System.out.println("Принтер занят");
        }
    }

    public void scan(int pageCount) {
        if (scanPageCount.get() == 0) {
            scanPageCount.set(pageCount);
        } else {
            System.out.println("Сканер занят");
        }
    }

}
