/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson2.entity;

import java.sql.*;

public class Product {
    private static final int PRODUCT_COUNT = 10000;

    public static void create(Connection connection) throws SQLException {
        String createProducts =
                "CREATE TABLE IF NOT EXISTS products ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + "prodId INTEGER UNIQUE NOT NULL,"
                        + "title TEXT NOT NULL,"
                        + "cost INTEGER NOT NULL"
                        + ");";
        String createIndexProductCost = "CREATE INDEX IF NOT EXISTS index_product_cost ON products (cost);";
        String createIndexProductProdId = "CREATE INDEX IF NOT EXISTS index_product_prodId ON products (prodId);";
        String createIndexProductTitle = "CREATE INDEX IF NOT EXISTS index_product_title ON products (title);";
        String clearProducts = "DELETE FROM products;";

        Statement statement = connection.createStatement();
        statement.execute(createProducts);
        statement.execute(createIndexProductCost);
        statement.execute(createIndexProductProdId);
        statement.execute(createIndexProductTitle);
        statement.execute(clearProducts);
    }

    public static void fill(Connection connection) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO products (prodId, title, cost) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 1; i <= PRODUCT_COUNT; i++) {
            statement.setInt(1, i);
            statement.setString(2, "товар" + i);
            statement.setInt(3, i * 10);
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();

        connection.setAutoCommit(autoCommit);
    }

    public static void printByTitle(Connection connection, String[] args) throws SQLException {
        printByTitle(connection, args[0]);
    }

    public static void printByTitle(Connection connection, String title) throws SQLException {
        String sql = "SELECT * FROM products WHERE title = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);

        ResultSet resultSet = statement.executeQuery();
        try {
            printFormat(resultSet);
        } finally {
            resultSet.close();
        }
    }

    public static void printFormat(ResultSet resultSet) throws SQLException {
        int id = resultSet.findColumn("id");
        int prodId = resultSet.findColumn("prodId");
        int title = resultSet.findColumn("title");
        int cost = resultSet.findColumn("cost");

        String format = "%-9s %-9s %-15s %s";

        System.out.printf(format, "id", "prodId", "cost", "cost");
        System.out.println();

        while (resultSet.next()) {
            System.out.printf(
                    format,
                    resultSet.getString(id),
                    resultSet.getString(prodId),
                    resultSet.getString(title),
                    resultSet.getString(cost)
            );
            System.out.println();
        }
    }

    public static void changeCost(Connection connection, String[] args) throws SQLException {
        String title = args[0];
        int cost = Integer.valueOf(args[1]);
        changeCost(connection, title, cost);
    }

    public static void changeCost(Connection connection, String title, int cost) throws SQLException {
        String sql = "UPDATE products SET cost = ? WHERE title = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setObject(1, cost);
        statement.setObject(2, title);

        statement.executeUpdate();
    }

    public static void printByCost(Connection connection, String[] args) throws SQLException {
        int coastFrom = Integer.valueOf(args[0]);
        int coastTo = Integer.valueOf(args[0]);
        printByCost(connection, coastFrom, coastTo);
    }

    public static void printByCost(Connection connection, int coastFrom, int coastTo) throws SQLException {
        String sql = "SELECT * FROM products WHERE cost BETWEEN ? and ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setObject(1, coastFrom);
        statement.setObject(2, coastTo);

        ResultSet resultSet = statement.executeQuery();
        printFormat(resultSet);

    }

}
