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

    public Employee() {
    }

    public Employee(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                '}';
    }
}
