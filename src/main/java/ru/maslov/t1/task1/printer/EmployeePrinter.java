package ru.maslov.t1.task1.printer;

import ru.maslov.t1.task1.entities.Employee;

import java.util.List;

/**
 * Интерфейс для функциональности вывода информации в файл
 */
public interface EmployeePrinter {
    void printEmployees(List<Employee> employees);
}
