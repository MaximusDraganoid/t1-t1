package ru.maslov.t1.task1.calculators;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Класс, отвечающий за расчет средней зп по отделу
 */
public class AverageDepartmentSalaryCalculator {
    private AverageDepartmentSalaryCalculator(){}

    public static BigDecimal calculateSalariesSum(Department department) {
        BigDecimal summarySalary = new BigDecimal(0);
        List<Employee> employees = department.getEmployees();
        for (Employee employee : employees) {
            summarySalary = summarySalary.add(employee.getSalary());
        }
        return summarySalary;
    }

    public static BigDecimal calculate(Department department) {
        BigDecimal averageSalary = calculateSalariesSum(department);

        return averageSalary.divide(new BigDecimal(department.getEmployees().size()),
                2,
                RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateWithEmployee(int numOfEmployeesInDepartment,
                                                   Employee employee,
                                                   BigDecimal summarySalary) {
        summarySalary = summarySalary.add(employee.getSalary());
        return summarySalary.divide(new BigDecimal(numOfEmployeesInDepartment + 1),
                2,
                RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateWithoutEmployee(int numOfEmployeesInDepartment,
                                                   Employee employee,
                                                   BigDecimal summarySalary) {
        summarySalary = summarySalary.subtract(employee.getSalary());
        return summarySalary.divide(new BigDecimal(numOfEmployeesInDepartment - 1),
                2,
                RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateWithEmployee(Department department, Employee employee){
        BigDecimal averageSalary = calculateSalariesSum(department);
        averageSalary = averageSalary.add(employee.getSalary());
        return averageSalary.divide(new BigDecimal(department.getEmployees().size() + 1),
                2,
                RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateWithoutEmployee(Department department, Employee employee) {
        BigDecimal averageSalary = new BigDecimal(0);
        List<Employee> employees = department.getEmployees();
        for (Employee emp : employees) {
            if (!emp.equals(employee)) {
                averageSalary = averageSalary.add(emp.getSalary());
            }
        }
        return averageSalary.divide(new BigDecimal(department.getEmployees().size() - 1),
                2,
                RoundingMode.HALF_UP);
    }
}
