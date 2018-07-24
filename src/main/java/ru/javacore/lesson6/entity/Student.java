/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson6.entity;

import lombok.Getter;

import java.sql.*;
import java.util.Objects;

public class Student {

    @Getter
    private int id;
    private String name;
    private int rating;

    public Student() {
    }

    public Student(int id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public static void createTable(Connection conn) throws SQLException {
        final String sql =
                "create table if not exists students ("
                        + "id integer primary key autoincrement not null,"
                        + "name text not null,"
                        + "rating integer not null"
                        + ");";
        Statement st = conn.createStatement();
        try {
            st.execute(sql);
        } finally {
            st.close();
        }
    }

    public static void insert(Connection conn, Student student) throws SQLException {
        final String sql = "insert into students (name, rating) values (?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        try {
            st.setObject(1, student.name);
            st.setObject(2, student.rating);
            st.execute();
        } finally {
            st.close();
        }
    }

    public static void update(Connection conn, Student student) throws SQLException {
        //final String sql = "update students set name = coalesce(?, name), rating = coalesce(?, rating) where id = ?";
        final String sql = "update students set name = ?, rating = ? where id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        try {
            st.setObject(1, student.name);
            st.setObject(2, student.rating);
            st.setObject(3, student.id);
            st.execute();
        } finally {
            st.close();
        }
    }

    public static Student select(Connection conn, int id) throws SQLException {
        final String sql = "select name, rating from students where id = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        try {
            st.setObject(1, id);
            ResultSet query = st.executeQuery();
            if (query.next()) {
                return new Student(id, query.getString("name"), query.getInt("rating"));
            }
        } finally {
            st.close();
        }
        return null;
    }

    public static int getLastInsertRowId(Connection conn) throws SQLException {
        final String sql = "select last_insert_rowid();";
        Statement st = conn.createStatement();
        try {
            ResultSet query = st.executeQuery(sql);
            if (query.next()) {
                return query.getInt(1);
            }
        } finally {
            st.close();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return rating == student.rating &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating);
    }

}
