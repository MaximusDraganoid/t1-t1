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

    public Transfer() {}

    public Transfer(Employee employee,
                    Department srcDepartment,
                    Department targetDepartment) {
        this.employee = employee;
        this.srcDepartment = srcDepartment;
        this.targetDepartment = targetDepartment;
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
}
