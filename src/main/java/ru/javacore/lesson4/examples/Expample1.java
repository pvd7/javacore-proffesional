/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson4.examples;

public class Expample1 {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }

    public static class Q {
        int n;
        boolean valueSet = false;

        synchronized int get() {
            while (!valueSet) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Exception");
                }
            }
            System.out.println("Пoлyчeнo : " + n);
            valueSet = false;
            notify();
            return n;
        }

        synchronized void put(int n) {
            while (valueSet) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Exception");
                }
            }
            this.n = n;
            valueSet = true;
            System.out.println("Oтпpaвлeнo : " + n);
            notify();
        }
    }

    public static class Producer implements Runnable {
        Q q;

        Producer(Q q) {
            this.q = q;
            new Thread(this, "Поставщик").start();
        }

        public void run() {
            int i = 0;
            while (true) {
                q.put(i++);
            }
        }
    }

    public static class Consumer implements Runnable {
        Q q;

        Consumer(Q q) {
            this.q = q;
            new Thread(this, " Потребитель ").start();
        }

        public void run() {
            while (true) {
                q.get();
            }
        }
    }

}
