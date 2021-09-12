package ru.maslov.t1.task1.validators;

import ru.maslov.t1.task1.exceptions.EmployeeValidationException;

import java.math.BigDecimal;

/**
 * Класс - валидатор входных данных о сотруднике
 */
public class EmployeeValidator {

    private EmployeeValidator() {}
    /**
     * Метод проверяет входящие данные о сотруднике, а так же подготавливает данные
     * для дальнейшей работы (удаляет лишние пробелы и т.п.)
     * @param empData - массив с данными о сотруднике
     * @throws EmployeeValidationException исключение валидации данных
     */
    public static void validateEmployeeData(String[] empData)
            throws EmployeeValidationException {

        if (empData.length != 3) {
            throw new EmployeeValidationException("В строке находится более или менее 3 колонок");
        }

        if (empData[0] == null
        || empData[1] == null
        || empData[2] == null) {
            throw new EmployeeValidationException("Одна из переданных строк в массиве является null");
        }

        for (int i = 0; i < empData.length; i++) {
            empData[i] = empData[i].trim();
        }

        if (checkNameToBlankOrUnacceptableSymbols(empData[0])) {
            throw new EmployeeValidationException("В имени есть недопустимый символ или строка пустая");
        }

        if (checkNumberToBlankOrUnacceptableSymbols(empData[1])) {
            throw new EmployeeValidationException("В значении ЗП есть недопустимый символ или строка пустая");
        }

        if (checkDepartmentNameToBlankOrUnacceptableSymbols(empData[2])) {
            throw new EmployeeValidationException("В названии департамента есть недопустимый символ" +
                    " или строка пустая");
        }

        BigDecimal salary;
        try {
            salary = new BigDecimal(empData[1]);
        } catch (NumberFormatException e) {
            throw new EmployeeValidationException("Допущена ошибка в указании ЗП: ЗП указана не " +
                    "корректно", e);
        }

        if (salary.scale() > 2) {
            throw new EmployeeValidationException("Допущена ошибка в указании ЗП: указанная ЗП " +
                    "неверное значение в копейках");
        }
    }

    private static boolean checkDepartmentNameToBlankOrUnacceptableSymbols(String str) {
        return str == null || str.length() == 0 || DepartmentNameValidator.check(str);
    }

    private static boolean checkNameToBlankOrUnacceptableSymbols(String str) {
        return str == null || str.length() == 0 || NameValidator.check(str);
    }

    private static boolean checkNumberToBlankOrUnacceptableSymbols(String str) {
        return str == null || str.length() == 0 || NumberValidator.check(str);
    }
}
