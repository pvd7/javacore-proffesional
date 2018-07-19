/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson5.entity;

import ru.javacore.lesson5.App;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Tunnel extends Stage {

    private static final Semaphore SEMAPHORE = new Semaphore(App.CARS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                if (!SEMAPHORE.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    SEMAPHORE.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " +
                        description);
            }
            SEMAPHORE.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

