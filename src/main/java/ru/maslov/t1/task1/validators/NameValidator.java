package ru.maslov.t1.task1.validators;

import java.util.regex.Pattern;

/**
 * Класс-валидатор для проверки корректности вводимых имен и названий департаментов
 */
public class NameValidator {

    private static Pattern namePattern = Pattern.compile("[\\d^!@#$%&*]");

    private NameValidator() {}

    public static boolean check(String name) {
        return namePattern.matcher(name).find();
    }
}
