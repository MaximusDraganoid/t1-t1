package ru.maslov.t1.task1.io.scanners;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс, предназначенный для считывания информации о
 * сорудниках из файла
 */
public interface EmployeeScanner {
    /**
     * Метод для считывания сотруднков из файла
     * @return мапа с департаментами
     */
    Map<String, Department> scanEmployees();
}
