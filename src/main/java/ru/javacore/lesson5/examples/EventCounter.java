/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson5.examples;

import java.util.concurrent.Phaser;

class EventCounter {

    private Phaser count = new Phaser(1);

    public void eventOccured() {
        count.arrive();
    }

    public void waitFor(int events) {
        count.register();

        for (int i = 0; i < events; i++) {
            count.arriveAndAwaitAdvance();
        }

        count.arriveAndDeregister();
    }

}
