/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson3;

import java.io.*;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws IOException {
        /**
         * 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
         */
        readFromFile("files/input1.txt", 0, 100);

        /**
         * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт)
         */
        String[] files = {"files/input1.txt", "files/input2.txt", "files/input3.txt", "files/input4.txt", "files/input5.txt"};
        concatFiles(files, "files/output.txt");

        /**
         * 3. Написать консольное приложение, которое умеет постранично читать текстовые файлы
         * (размером > 10 mb). Вводим страницу (за страницу можно принять 1800 символов), программа
         * выводит ее в консоль. Контролируем время выполнения: программа не должна загружаться
         * дольше 10 секунд, а чтение – занимать свыше 5 секунд.
         */
        System.out.println();
        readFiles();
    }

    /**
     * Читает файл в байтовый массив и выводит этот массив в консоль
     *
     * @param filename имя файла
     * @param off      смещение
     * @param len      длина
     */
    private static void readFromFile(String filename, long off, long len) {
        int b;
        long i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.skip(off);
            while (((b = br.read()) != -1) && (i++ != len)) {
                System.out.print((char) b);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Содиняет массив файлов в один
     *
     * @param files   исходные файлы
     * @param outFile конечный файл
     * @throws IOException исключение
     */
    private static void concatFiles(String[] files, String outFile) throws IOException {
        List<FileInputStream> fileList = new ArrayList<>();
        try {
            for (String file : files) {
                fileList.add(new FileInputStream(file));
            }
            try (SequenceInputStream seq = new SequenceInputStream(Collections.enumeration(fileList));
                 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile))) {
                int b;
                while ((b = seq.read()) != -1) {
                    out.write(b);
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            for (InputStream file : fileList) {
                file.close();
            }
        }
    }

    /**
     * Читает постранично данные из файла
     */
    private static void readFiles() {
        String file = "D:\\Install\\acrobat9pro.rar";
        final int page_size = 1800;

        Scanner sc = new Scanner(System.in);
        int page;
        long startTime;
        while (true) {
            System.out.print("Input page number: ");
            page = sc.nextInt();

            startTime = System.nanoTime();
            readFromFile(file, page * page_size, page_size);
            System.out.printf("Time %d ms", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));
            System.out.println();
        }
    }

}
