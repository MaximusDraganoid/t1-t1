package ru.maslov.t1.task1.entities;

/**
 * Сотрудник
 */
public class Employee {
    /**
     * Имя сотрднка
     */
    private String name;
    /**
     * ЗП сотрудника
     */
    private Integer salary; //todo: change to something else
    /**
     * Департамент, в котором работает струдник
     */
    private Department department;

    public Employee() {
    }

    public Employee(String name, Integer salary, Department department) {
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
