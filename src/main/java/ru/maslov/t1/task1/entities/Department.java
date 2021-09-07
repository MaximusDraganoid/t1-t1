package ru.maslov.t1.task1.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Департамент
 */
public class Department {
    /**
     * Список сотрудников данного депатамента
     */
    private List<Employee> employees;

    /**
     * Название департамента
     */
    private String name;

    public Department() {
        employees = new LinkedList<>();
    }

    public Department(List<Employee> employees, String name) {
        this.employees = employees;
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "employees=" + employees +
                ", name='" + name + '\'' +
                '}';
    }
}
