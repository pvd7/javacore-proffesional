/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson2.entity;

/**
 * Консольные команды
 */
public enum ConsoleCommand {
    HELP("/?", "help"),
    PRICE("/цена", "узнать цену товара"),
    CHANGE_PRICE("/сменитьцену", "сменить цену товара"),
    FIND_PRICE("/товарыпоцене", "вывести товры заданном ценовом диапазоне"),
    QUIT("/выход", "выход из приложения");

    private String name;
    private String hint;

    ConsoleCommand(String name, String hint) {
        this.name = name;
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public String getHint() {
        return hint;
    }

    /**
     * Выводит в консоль все команды
     */
    public static void print() {
        String fmt = "%-20s %s";
        System.out.printf(fmt, "name", "hint");
        System.out.println();
        for (ConsoleCommand command : ConsoleCommand.values()) {
            System.out.printf(fmt, command.getName(), command.getHint());
            System.out.println();
        }
    }
}
