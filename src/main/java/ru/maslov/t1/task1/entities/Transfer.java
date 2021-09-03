package ru.maslov.t1.task1.entities;

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
     * Департамент для перехдода
     */
    private Department targetDepartment;

    public Transfer(Employee employee, Department targetDepartment) {
        this.employee = employee;
        this.targetDepartment = targetDepartment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Department getTargetDepartment() {
        return targetDepartment;
    }
}
