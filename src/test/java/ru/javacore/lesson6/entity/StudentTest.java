/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson6.entity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class StudentTest {

    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[]{
                new Student(1, "Petrov1", 100),
                new Student(2, "Petrov2", 200),
                new Student(3, "Petrov3", 300),
                new Student(4, "Petrov4", 400),
                new Student(5, "Petrov5", 500),
                new Student(6, "Petrov6", 600),
                new Student(7, "Petrov7", 700),
                new Student(8, "Petrov8", 800)
        });
    }

    private Student student;

    public StudentTest(Student student) {
        this.student = student;
    }

    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String dbPath = "./files/test.db";
        conn = DriverManager.getConnection(JDBC.PREFIX + dbPath);
        conn.setAutoCommit(false);
        Student.createTable(conn);
    }

    @After
    public void tearDown() throws Exception {
        conn.rollback();
        conn.close();
    }

    @Test
    public void insertAndSelect() throws SQLException {
        Student.insert(conn, this.student);
        Student student = Student.select(conn, Student.getLastInsertRowId(conn));
        Assert.assertEquals(this.student, student);
    }

    @Test
    public void updateAndSelect() throws SQLException {
        Student.insert(conn, this.student);
        int id = Student.getLastInsertRowId(conn);

        Student student = new Student(id, "Ivan0", 50);
        Student.update(conn, student);

        Student student1 = Student.select(conn, id);
        Assert.assertEquals(student, student1);
    }

}