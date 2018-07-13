/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson3;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws IOException {
//        writeToFile("files/input10.txt", 1000000);

//        1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
        char[] chars = readFromFile("files/input1.txt", 0, 100);
        System.out.println(chars);

//        2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт)
        String[] files = {"files/input1.txt", "files/input2.txt", "files/input3.txt", "files/input4.txt", "files/input5.txt"};
        concatFiles(files, "files/output.txt");

//        3. Написать консольное приложение, которое умеет постранично читать текстовые файлы
//           (размером > 10 mb). Вводим страницу (за страницу можно принять 1800 символов), программа
//           выводит ее в консоль. Контролируем время выполнения: программа не должна загружаться
//           дольше 10 секунд, а чтение – занимать свыше 5 секунд.
        System.out.println();
        readFiles();
    }

    private static void writeToFile(String file, long count) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < count; i++) {
                bw.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Читает файл в байтовый массив и выводит этот массив в консоль
     *
     * @param file имя файла
     * @param off  смещение
     * @param len  длина
     */
    private static char[] readFromFile(String file, long off, int len) {
        char[] chars = new char[len];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.skip(off);
            br.read(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chars;
    }

    private static byte[] randomReadFile(String file, long off, int len) {
        byte[] b = new byte[len];
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.seek(off);
            raf.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
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
        file = "D:\\db\\rni_165.fbk";
        file = "files\\input1.txt";
        final int page_size = 100;

        Scanner sc = new Scanner(System.in);
        int page;
        long startTime;
        while (true) {
            System.out.println();
            System.out.print("Input page number: ");
            page = sc.nextInt();

            startTime = System.nanoTime();
            byte[] bytes = randomReadFile(file, page * page_size, page_size);
            System.out.printf("Time %d ms", TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime));
            System.out.println();
            System.out.println(Arrays.toString(bytes));
        }
    }

//    private static byte[] charToBytes(char[] chars) {
//        CharBuffer charBuffer = CharBuffer.wrap(chars);
//        ByteBuffer byteBuffer = Charset.forName("UTF-16").encode(charBuffer);
//        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
//                byteBuffer.position(), byteBuffer.limit());
//        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
//        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
//        return bytes;
//    }

}
