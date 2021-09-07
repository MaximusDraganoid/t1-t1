package ru.maslov.t1.task1.validators;

import java.util.regex.Pattern;

/**
 * Класс-валидатор для проверки корректности вводимых чисел (в данной задаче - ЗП)
 */
public class NumberValidator {
    private static Pattern numberPattern = Pattern.compile("[^\\d\\.]");

    private NumberValidator() {}

    public static boolean check(String name) {
        return numberPattern.matcher(name).find();
    }
}
