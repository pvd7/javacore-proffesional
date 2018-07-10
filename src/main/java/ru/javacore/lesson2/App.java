/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson2;

import org.sqlite.JDBC;
import ru.javacore.lesson2.entity.ConsoleCommand;
import ru.javacore.lesson2.entity.Product;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        final String DB_PATH = "../test.db";

        Connection connection = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        try {
            Product.create(connection);
            Product.fill(connection);
            ConsoleCommand.print();

            String nextLine;
            String strCommand;
            PrintStream out = System.out;
            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.print("cmd>");
                nextLine = scanner.nextLine();
                for (ConsoleCommand consoleCommand : ConsoleCommand.values()) {
                    strCommand = consoleCommand.getName();
                    if (nextLine.startsWith(strCommand)) {
                        nextLine = nextLine.substring(strCommand.length()).trim();
                        switch (consoleCommand) {
                            case HELP:
                                ConsoleCommand.print();
                                break;
                            case PRICE:
                                Product.printByTitle(connection, nextLine.split(" "));
                                break;
                            case CHANGE_PRICE:
                                Product.changePrice(connection, nextLine.split(" "));
                                break;
                            case FIND_PRICE:
                                Product.findByPrice(connection, nextLine.split(" "));
                                break;
                            case QUIT:
                                out.println("by by :)");
                                System.exit(0);
                                break;
                        }
                        break;
                    }
                }
            }
        } finally {
            connection.close();
        }
    }

}
