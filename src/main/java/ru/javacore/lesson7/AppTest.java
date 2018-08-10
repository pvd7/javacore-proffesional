/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson7;

import lombok.extern.slf4j.Slf4j;
import ru.javacore.lesson7.annotation.AfterSuite;
import ru.javacore.lesson7.annotation.BeforeSuite;
import ru.javacore.lesson7.annotation.Test;
import ru.javacore.lesson7.annotation.TestRunner;

@Slf4j
public class AppTest {

    public static void main(String[] args) {
        TestRunner.run(AppTest.class);
    }

    @BeforeSuite
    public void beforeSuite() { log.info("beforeSuite"); }

    @AfterSuite
    public void afterSuite() {
        log.info("afterSuite");
    }

    @Test(priority = 1)
    public void test1() {
        log.info("test1");
    }

    @Test(priority = 2)
    public void test2() {
        log.info("test2");
    }

    @Test
    public void test() {
        log.info("test");
    }

    @Test(priority = 3)
    public void test3() {
        log.info("test3");
    }

    @Test
    public void test0() {
        log.info("test0");
    }

    @Test(priority = 4)
    public void test4() {
        log.info("test4");
    }
}
