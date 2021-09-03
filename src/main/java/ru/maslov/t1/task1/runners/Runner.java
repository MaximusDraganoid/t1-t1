package ru.maslov.t1.task1.runners;

import ru.maslov.t1.task1.calculators.AverageDepartmentSalaryCalculator;
import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.scanners.EmployeeScanner;
import ru.maslov.t1.task1.scanners.impl.EmployeeScannerFromTxtFileImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        /**
         * Написать программу, которая читает из файла информацию о
         * сотрудниках и их принадлежности к отделам, рассчитывает
         * среднюю зарплату сотрудников в отделе, строит и выводит в
         * файл все варианты возможных переводов сотрудников из одного
         * отдела в другой, при которых средняя зарплата отдела увеличивается
         * в обоих отделах.
         */
        /**
         * - класс, отвечающий за считывание данных о сотруднике из файла
         * - класс, отвеающий за расчет средней зп по отделу
         * - класс, который строит все все варианты возможных переводов
         * - класс, который выводит все эти варианты в отдельный файл
         */
        /**
         * Что мне не нравится самому:
         * - приходится переавать список департаментов (не знаю как решаить эту пробелму иначе)
         * получается, что функция выполняет несколько задач (как мне кажется), а именно
         * она считывает данные из файла, создает список работницков и заполняет список департаментов
         */

        EmployeeScanner scanner = new EmployeeScannerFromTxtFileImpl(
                new File("test.txt"));

        List<Department> departments = new LinkedList<>();
        List<Employee> employees = scanner.findEmployees(departments);

        System.out.println("Employees");
        System.out.println(employees);
        System.out.println("Departments");
        System.out.println(departments);

        departments
                .forEach(department -> {
                    System.out.println("For department " + department.getName()
                    + " average salary is " + AverageDepartmentSalaryCalculator.calculate(department));
                });
    }
}
