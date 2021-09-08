package ru.maslov.t1.task1.io.printers;

import ru.maslov.t1.task1.entities.Department;

/**
 * Интерфейс для вывода информации о дпартаментах
 */
public interface DepartmentInfoPrinter {
    void printMainDepartmentInfo(Department department);
    void printEmployeeInfo(Department department);
}
