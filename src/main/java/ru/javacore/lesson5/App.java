/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson5;

import ru.javacore.lesson5.entity.Car;
import ru.javacore.lesson5.entity.Race;
import ru.javacore.lesson5.entity.Road;
import ru.javacore.lesson5.entity.Tunnel;
import java.util.concurrent.CyclicBarrier;

public class App {

    public static final int CARS_COUNT = 4;

    public static final CyclicBarrier START = new CyclicBarrier(App.CARS_COUNT, () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));

    public static final CyclicBarrier FINISH = new CyclicBarrier(App.CARS_COUNT, () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!"));

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (Car car : cars) {
            new Thread(car).start();
        }
    }

}
