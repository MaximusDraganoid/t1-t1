package ru.maslov.t1.task1.calculators;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;

import java.math.BigDecimal;
import java.util.List;

/**
 * Класс, отвечающий за расчет средней зп по отделу
 */
public class AverageDepartmentSalaryCalculator {
    private AverageDepartmentSalaryCalculator(){}

    public static BigDecimal calculate(Department department) {
        BigDecimal averageSalary = new BigDecimal(0);
        List<Employee> employees = department.getEmployees();
        for (Employee employee : employees) {
            averageSalary = averageSalary.add(employee.getSalary());
        }
        averageSalary = averageSalary.setScale(2, 2);
        return averageSalary.divide(new BigDecimal(employees.size()), 2);
    }
}
