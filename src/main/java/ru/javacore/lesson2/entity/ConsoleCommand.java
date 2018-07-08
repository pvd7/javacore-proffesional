/*
 * Copyright (c) 2018.
 * @author Pavel Dymov
 */

package ru.javacore.lesson2.entity;

import java.util.HashMap;
import java.util.Map;

public enum ConsoleCommand {
    HELP("/?", "help"),
    COST("/цена", "узнать цену товара"),
    COST_CHANGE("/сменитьцену", "сменить цену товара"),
    COST_FIND("/товарыпоцене", "вывести товры заданном ценовом диапазоне"),
    QUIT("/выход", "выход из прриложения");

    private String name;
    private String hint;

    ConsoleCommand(String name, String hint) {
        this.name = name;
        this.hint = hint;
    }

    /**
     * Список консольныйх команд (для быстрого поиска по имени)
     */
    private static Map<String, ConsoleCommand> commandMap = new HashMap<>(3);

    // Заполняет список консольных команд
    static {
        for (ConsoleCommand command : ConsoleCommand.values()) {
            commandMap.put(command.name, command);
        }
    }

    /**
     * Ищет консольную команду по имени
     *
     * @param name имя команды
     * @return команда
     */
    public ConsoleCommand find(String name) {
        return commandMap.get(name);
    }

    public String getName() {
        return name;
    }

    public String getHint() {
        return hint;
    }

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
