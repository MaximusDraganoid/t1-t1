package ru.maslov.t1.task1.entities;

import java.util.List;

/**
 * Класс для хранения информации о переводах групп сотрудников
 */
public class GroupTransfer {
    /**
     * Департамент откуда уходят
     */
    private Department src;
    /**
     * департамент куда переводятся
     */
    private Department dst;
    /**
     * Список переводящихся сотрудников
     */
    private List<Employee> employees;

    public GroupTransfer(Department src, Department dst, List<Employee> employees) {
        this.src = src;
        this.dst = dst;
        this.employees = employees;
    }

    public Department getSrc() {
        return src;
    }

    public Department getDst() {
        return dst;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
