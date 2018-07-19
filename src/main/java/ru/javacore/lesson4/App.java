/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4;

import ru.javacore.lesson4.mfd.MultifunctionDevice;
import ru.javacore.lesson4.printqueue.Controller;
import ru.javacore.lesson4.printqueue.PrintString;

import java.io.FileWriter;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок
        //– ABСABСABС). Используйте wait/notify/notifyAll.
        PrintString.main(null);

        // 2. Написать небольшой метод, в котором 3 потока построчно пишут данные в файл (по 10
        // записей с периодом в 20 мс).
        threadsWriteToFile();

        //3. Написать класс МФУ, на котором возможно одновременно выполнять печать и сканирование
        //документов, но нельзя одновременно печатать или сканировать два документа. При печати в
        //консоль выводится сообщения «Отпечатано 1, 2, 3,... страницы», при сканировании –
        //аналогично «Отсканировано...». Вывод в консоль с периодом в 50 мс.
        MultifunctionDevice.main(null);
    }


    private static void threadsWriteToFile() throws IOException, InterruptedException {
        FileWriter fw = new FileWriter("files/output4.txt");
        Thread thread1 = new Thread(() -> outFile(fw));
        thread1.start();
        Thread thread2 = new Thread(() -> outFile(fw));
        thread2.start();
        Thread thread3 = new Thread(() -> outFile(fw));
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
    }

    private static void outFile(FileWriter fw) {
        try {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 20; j++) {
                    synchronized (fw) {
                        fw.write(Thread.currentThread().getName() + "\n");
                    }
                }
                Thread.sleep(20);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
