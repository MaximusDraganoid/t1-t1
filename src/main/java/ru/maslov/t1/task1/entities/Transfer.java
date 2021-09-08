package ru.maslov.t1.task1.entities;

import java.math.BigDecimal;

/**
 * Класс для хранения информации о том какой сотрудник
 * в какой департамент может перейти
 */
public class Transfer {
    /**
     * Сотрудник
     */
    private Employee employee;

    /**
     * Депатамент в котором был сотрудник до этого
     */
    private Department srcDepartment;

    /**
     * Департамент для перехдода
     */
    private Department targetDepartment;
    /**
     * Исходная средняя зарплата в отделе, из которого совершается перевод
     */
    private BigDecimal srcPrevAverageSalary;
    /**
     * Новая средняя зарплата в отделе, из которого совершается перевод
     */
    private BigDecimal srcNewAverageSalary;
    /**
     * Исходная средняя зарплата в отделе, в который совершается перевод
     */
    private BigDecimal targetPrevAverageSalary;
    /**
     * Новая средняя зарплата в отделе, в который совершается перевод
     */
    private BigDecimal targetNewAverageSalary;

    public Transfer() {}

    public Transfer(Employee employee,
                    Department srcDepartment,
                    Department targetDepartment,
                    BigDecimal srcPrevAverageSalary,
                    BigDecimal srcNewAverageSalary,
                    BigDecimal targetPrevAverageSalary,
                    BigDecimal targetNewAverageSalary) {
        this.employee = employee;
        this.srcDepartment = srcDepartment;
        this.targetDepartment = targetDepartment;
        this.srcPrevAverageSalary = srcPrevAverageSalary;
        this.srcNewAverageSalary = srcNewAverageSalary;
        this.targetPrevAverageSalary = targetPrevAverageSalary;
        this.targetNewAverageSalary = targetNewAverageSalary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Department getTargetDepartment() {
        return targetDepartment;
    }

    public Department getSrcDepartment() {
        return srcDepartment;
    }

    public BigDecimal getSrcPrevAverageSalary() {
        return srcPrevAverageSalary;
    }

    public BigDecimal getSrcNewAverageSalary() {
        return srcNewAverageSalary;
    }

    public BigDecimal getTargetPrevAverageSalary() {
        return targetPrevAverageSalary;
    }

    public BigDecimal getTargetNewAverageSalary() {
        return targetNewAverageSalary;
    }
}
