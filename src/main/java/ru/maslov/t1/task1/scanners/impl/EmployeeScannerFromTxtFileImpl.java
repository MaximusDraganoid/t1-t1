package ru.maslov.t1.task1.scanners.impl;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.scanners.EmployeeScanner;
import ru.maslov.t1.task1.utils.DepartmentUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Релизация считывателя сотрудников, основнанная на считывании из файла
 */
public class EmployeeScannerFromTxtFileImpl
        implements EmployeeScanner {
    /**
     * Объект - считыватель из файла
     */
    private String txtFilePath;

    public EmployeeScannerFromTxtFileImpl(String txtFilePath) {
        this.txtFilePath = txtFilePath;
    }

    @Override
    public List<Employee> scanEmployees(List<Department> departments) {
        List<Employee> employees = new LinkedList<>();
        try(BufferedReader br =
                    new BufferedReader(new FileReader(txtFilePath))) {
            String employeeInfo;
            while ((employeeInfo = br.readLine()) != null) {
                employees.add(parseEmployee(employeeInfo, departments));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }


    /**
     * Метод для создания нового пользователя на основании записи о нем
     * @param employeeInfo передается в формате: имя_сотрудника зп название_департамента
     * @return возвращает созданного сотрудника
     */
    private Employee parseEmployee(String employeeInfo, List<Department> departments) {
        String[] empData = employeeInfo.
                split(" ");
        Employee newEmployee = new Employee();
        newEmployee.setName(empData[0]);
        newEmployee.setSalary(new BigDecimal(empData[1]));
        Department department = DepartmentUtils.findDepartmentByName(departments, empData[2]);
        newEmployee.setDepartment(department);
        DepartmentUtils.addEmployeeToDepartment(newEmployee, department);
        return newEmployee;
    }
}
