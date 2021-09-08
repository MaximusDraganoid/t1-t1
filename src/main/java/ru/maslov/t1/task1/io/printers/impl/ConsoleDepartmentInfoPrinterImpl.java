package ru.maslov.t1.task1.io.printers.impl;

import ru.maslov.t1.task1.calculators.AverageDepartmentSalaryCalculator;
import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.io.printers.DepartmentInfoPrinter;

/**
 * Класс, отвечающий за отображение информации о департаменте и его сотрудниках
 */
public class ConsoleDepartmentInfoPrinterImpl implements DepartmentInfoPrinter {

    private static final String dataPattern = "%-60.50s %30.2f" + System.getProperty("line.separator");

    private static final String headerPattern = "%-60.50s %30.25s" + System.getProperty("line.separator");

    @Override
    public void printMainDepartmentInfo(Department department) {
        printHeaderLine("Наименование департамента", "Средняя ЗП");
        System.out.printf(dataPattern, department.getName(),
                AverageDepartmentSalaryCalculator.calculate(department));
        System.out.println();
    }

    @Override
    public void printEmployeeInfo(Department department) {
        writeLine();
        System.out.println("Список сотрудников отдела " + department.getName());
        printHeaderLine("Имя сотруднка", "ЗП сотрудника");
        department.getEmployees().forEach(employee ->
                System.out.printf(dataPattern, employee.getName(), employee.getSalary()));
        System.out.println();
    }

    private void printHeaderLine(String firstColumnName, String secondColumnName) {
        writeLine();
        System.out.printf(headerPattern, firstColumnName, secondColumnName);
        writeLine();
    }

    private void writeLine() {
        for (int i = 0; i < 90; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
