/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.utils;

import org.junit.Assert;
import org.junit.Test;

import static ru.javacore.utils.StringUtils.getFirstWord;
import static ru.javacore.utils.StringUtils.isEmpty;

public class StringUtilsTest {

    @Test
    public void getFirstWordTest() {
        Assert.assertEquals("foo", getFirstWord("foo qe"));
        Assert.assertEquals("foo", getFirstWord("foo"));
        Assert.assertEquals("", getFirstWord(""));

        Assert.assertNotEquals("fo", getFirstWord("foo qe"));
        Assert.assertNotEquals("fo", getFirstWord("foo"));
        Assert.assertNotEquals("", getFirstWord("foo"));
    }

    @Test
    public void isEmptyTest() {
        Assert.assertFalse(isEmpty("foo qe"));
        Assert.assertTrue(isEmpty(""));
        Assert.assertTrue(isEmpty(null));
    }
}