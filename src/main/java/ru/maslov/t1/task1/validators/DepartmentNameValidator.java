package ru.maslov.t1.task1.validators;

import java.util.regex.Pattern;

/**
 * Класс-валидатор для проверки корректности названий департаментов
 */
public class DepartmentNameValidator {
    private DepartmentNameValidator() {}

    private static Pattern depNamePattern = Pattern.compile("[!@#$%&*]");
    public static boolean check(String str) {
        return depNamePattern.matcher(str).find();
    }
}
