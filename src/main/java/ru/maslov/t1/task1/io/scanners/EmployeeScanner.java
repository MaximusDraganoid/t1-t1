package ru.maslov.t1.task1.io.scanners;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;

import java.util.List;

/**
 * Интерфейс, предназначенный для считывания информации о
 * сорудниках из файла
 */
public interface EmployeeScanner {
    /**
     * Метод для считывания сотруднков из файла
     * @param departments - список департаментов
     * @return список сотрудников
     */
    List<Employee> scanEmployees(List<Department> departments);
}
