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

    /**
     * Рассчет суммарной ЗП департамента
     * @param department - целевой департамент
     * @return
     */
    public static BigDecimal calculateSalariesSum(Department department) {
        BigDecimal summarySalary = new BigDecimal(0);
        List<Employee> employees = department.getEmployees();
        for (Employee employee : employees) {
            summarySalary = summarySalary.add(employee.getSalary());
        }
        return summarySalary;
    }

    /**
     * Рассчет средней ЗП департамент
     * @param department
     * @return
     */
    public static BigDecimal calculate(Department department) {
        BigDecimal averageSalary = calculateSalariesSum(department);

        return averageSalary.divide(new BigDecimal(department.getEmployees().size()),
                2,
                RoundingMode.HALF_UP);
    }

    /**
     * Рассчет средней зп департамента с учетом
     * @param department - департамент для которого производится рассчет
     * @param employee - сотрудник с учетом которого провоится расчет
     * @return
     */
    public static BigDecimal calculateWithEmployee(Department department, Employee employee){
        BigDecimal averageSalary = calculateSalariesSum(department);
        averageSalary = averageSalary.add(employee.getSalary());
        return averageSalary.divide(new BigDecimal(department.getEmployees().size() + 1),
                2,
                RoundingMode.HALF_UP);
    }

    /**
     *
     * @param department - департамент для которого проиизводится расчет
     * @param employee - сотлрудник без которого проводится расчет
     * @return
     */
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
