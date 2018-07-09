/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson2.entity;

import java.sql.*;

public class Product {
    private static final int PRODUCT_COUNT = 10000;

    /**
     * Создает таблицу и индексы
     *
     * @param connection подключение к БД
     * @throws SQLException исключение
     */
    public static void create(Connection connection) throws SQLException {
        String createProducts =
                "CREATE TABLE IF NOT EXISTS products ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + "prodId INTEGER UNIQUE NOT NULL,"
                        + "title TEXT NOT NULL,"
                        + "price INTEGER NOT NULL"
                        + ");";
        String createIndexProductPrice = "CREATE INDEX IF NOT EXISTS index_product_price ON products (price);";
        String createIndexProductProdId = "CREATE INDEX IF NOT EXISTS index_product_prodId ON products (prodId);";
        String createIndexProductTitle = "CREATE INDEX IF NOT EXISTS index_product_title ON products (title);";
        String clearProducts = "DELETE FROM products;";

        Statement statement = connection.createStatement();
        statement.execute(createProducts);
        statement.execute(createIndexProductPrice);
        statement.execute(createIndexProductProdId);
        statement.execute(createIndexProductTitle);
        statement.execute(clearProducts);
    }

    /**
     * Заполняет таблицу данными
     *
     * @param connection подключение к БД
     * @throws SQLException исключени
     */
    public static void fill(Connection connection) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO products (prodId, title, price) VALUES (?, ?, ?)";
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

    /**
     * Выводит в консоль товары по имени
     *
     * @param connection подключение к БД
     * @param args       агрументы, 0 - наименование товара
     * @throws SQLException исключение
     */
    public static void printByTitle(Connection connection, String[] args) throws SQLException {
        printByTitle(connection, args[0]);
    }

    /**
     * Выводит в консоль товары по имени
     *
     * @param connection подключение к БД
     * @param title      наименование товара
     * @throws SQLException исключение
     */
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

    /**
     * Выводит в консоль форматированые данные из базы
     *
     * @param resultSet результат запроса
     * @throws SQLException исключение
     */
    public static void printFormat(ResultSet resultSet) throws SQLException {
        int id = resultSet.findColumn("id");
        int prodId = resultSet.findColumn("prodId");
        int title = resultSet.findColumn("title");
        int price = resultSet.findColumn("price");

        String format = "%-9s %-9s %-15s %s";

        System.out.printf(format, "id", "prodId", "title", "price");
        System.out.println();

        while (resultSet.next()) {
            System.out.printf(
                    format,
                    resultSet.getString(id),
                    resultSet.getString(prodId),
                    resultSet.getString(title),
                    resultSet.getString(price)
            );
            System.out.println();
        }
    }

    /**
     * Меняет цену товара
     *
     * @param connection подвлючение к БД
     * @param args       аргументы, 0 - наименование товара, 1 - новая цена
     * @throws SQLException исключение
     */
    public static void changePrice(Connection connection, String[] args) throws SQLException {
        String title = args[0];
        int price = Integer.valueOf(args[1]);
        changePrice(connection, title, price);
    }

    /**
     * Меняет цену товара
     *
     * @param connection подвлючение к БД
     * @param title      наименование товара
     * @param price       новая цена
     * @throws SQLException исключение
     */
    public static void changePrice(Connection connection, String title, int price) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE title = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setObject(1, price);
        statement.setObject(2, title);

        int count = statement.executeUpdate();
        System.out.println("rows affected " + count);
    }

    /**
     * Выводит в консоль товары в заданном ценовом диапазоне
     *
     * @param connection подключение к БД
     * @param args       агрументы, 0 - ценат от, 1 - цена до
     * @throws SQLException исключение
     */
    public static void findByPrice(Connection connection, String[] args) throws SQLException {
        int coastFrom = Integer.valueOf(args[0]);
        int coastTo = Integer.valueOf(args[1]);
        findByPrice(connection, coastFrom, coastTo);
    }

    /**
     * Выводит в консоль товары в заданном ценовом диапазоне
     *
     * @param connection подключение к БД
     * @param priceFrom  ценат от
     * @param priceTo    цена до
     * @throws SQLException исключение
     */
    public static void findByPrice(Connection connection, int priceFrom, int priceTo) throws SQLException {
        String sql = "SELECT * FROM products WHERE price BETWEEN ? and ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setObject(1, priceFrom);
        statement.setObject(2, priceTo);

        ResultSet resultSet = statement.executeQuery();
        printFormat(resultSet);
    }

}
