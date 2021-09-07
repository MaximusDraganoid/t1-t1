package ru.maslov.t1.task1.validators;

import org.apache.commons.lang3.StringUtils;
import ru.maslov.t1.task1.exceptions.EmployeeValidationException;

import java.math.BigDecimal;

/**
 * Класс - валидатор входных данных о сотруднике
 */
public class EmployeeValidator {
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

        if (checkNameToBlankOrUnacceptableSymbols(empData[0])) {
            throw new EmployeeValidationException("В имени есть недопустимый или строка пустая");
        }

        if (checkNumberToBlankOrUnacceptableSymbols(empData[1])) {
            throw new EmployeeValidationException("В значении ЗП есть недопустимый или строка пустая");
        }

        if (checkNameToBlankOrUnacceptableSymbols(empData[2])) {
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

        if (salary.compareTo(new BigDecimal(0)) < 0) {
            throw new EmployeeValidationException("Допущена ошибка в указании ЗП: указанная ЗП " +
                    "меньше 0");
        }

        if (salary.scale() > 2) {
            throw new EmployeeValidationException("Допущена ошибка в указании ЗП: указанная ЗП " +
                    "неверное значение в копейках");
        }
    }

    private static boolean checkNameToBlankOrUnacceptableSymbols(String str) {
        return StringUtils.isBlank(str) || NameValidator.check(str);
    }

    private static boolean checkNumberToBlankOrUnacceptableSymbols(String str) {
        return StringUtils.isBlank(str) || NumberValidator.check(str);
    }
}
