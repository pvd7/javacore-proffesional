/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson6;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void split() {
        Assert.assertArrayEquals(new int[]{5, 6}, App.split(new int[]{1, 3, 4, 5, 6}));
        Assert.assertArrayEquals(new int[]{3, 1, 5, 6}, App.split(new int[]{4, 3, 1, 5, 6}));
        Assert.assertArrayEquals(new int[]{}, App.split(new int[]{4, 3, 4, 5, 4}));
    }

    @Test(expected = RuntimeException.class)
    public void splitException1() {
        Assert.assertArrayEquals(new int[]{}, App.split(new int[]{1, 3, 1, 5, 1}));
    }

    @Test(expected = RuntimeException.class)
    public void splitException2() {
        Assert.assertArrayEquals(new int[]{}, App.split(new int[]{}));
    }

    @Test
    public void check() {
        Assert.assertTrue(App.check(new int[]{1, 4}));
        Assert.assertTrue(App.check(new int[]{1, 1, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4}));
        Assert.assertFalse(App.check(new int[]{0, 1, 4, 4, 1, 4, 4, 4, 4, 1, 4, 4}));
        Assert.assertFalse(App.check(new int[]{}));
        Assert.assertFalse(App.check(new int[]{1, 4, 4, 1, 4, 0, 4, 4, 1, 4, 4}));
        Assert.assertFalse(App.check(new int[]{1, 4, 4, 1, 4, 4, 4, 1, 4, 0}));
    }
}