/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson5.entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Tunnel extends Stage {

    private final Semaphore semaphore = new Semaphore(Race.CARS_COUNT / 2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                if (!semaphore.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                    semaphore.acquire();
                }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

