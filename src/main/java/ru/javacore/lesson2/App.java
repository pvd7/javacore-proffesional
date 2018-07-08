/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson2;

import org.sqlite.JDBC;
import ru.javacore.lesson2.entity.ConsoleCommand;
import ru.javacore.lesson2.entity.Product;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        final String DB_PATH = "../test.db";

        Connection conn = DriverManager.getConnection(JDBC.PREFIX + DB_PATH);
        try {
//            Product.create(conn);
//            Product.fill(conn);



            String nextLine;
            String strCommand;
            PrintWriter printWriter = new PrintWriter(System.out, true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                nextLine = scanner.nextLine();
                for (ConsoleCommand consoleCommand : ConsoleCommand.values()) {
                    strCommand = consoleCommand.getName();
                    if (nextLine.startsWith(strCommand)) {
                        nextLine = nextLine.substring(strCommand.length()).trim();
                        switch (consoleCommand) {
                            case HELP:
                                ConsoleCommand.print();
                                break;
                            case COST:
                                Product.printByTitle(conn, nextLine.split(" "));
                                break;
                            case COST_CHANGE:
                                Product.changeCost(conn, nextLine.split(" "));
                                break;
                            case COST_FIND:
                                Product.printByCost(conn, nextLine.split(" "));
                                break;
                            case QUIT:
                                printWriter.println("by by :)");
                                System.exit(0);
                                break;
                        }
                    }
                }
            }


//            System.out.println();
//            Product.printByCost(conn, 20, 30);
        } finally {
            conn.close();
        }
    }

}
