package ru.maslov.t1.task1.entities;

import java.math.BigDecimal;

/**
 * Сотрудник
 */
public class Employee {
    /**
     * Имя сотрднка
     */
    private String name;

    /**
     * ЗП сотрудника (предполагается, что зп в рублях)
     */
    private BigDecimal salary;

    /**
     * Департамент, в котором работает струдник
     */
    private Department department;

    public Employee() {
    }

    public Employee(String name, BigDecimal salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department.getName() +
                '}';
    }
}
