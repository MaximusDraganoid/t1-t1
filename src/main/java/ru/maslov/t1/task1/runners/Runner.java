package ru.maslov.t1.task1.runners;

import ru.maslov.t1.task1.calculators.AverageDepartmentSalaryCalculator;
import ru.maslov.t1.task1.calculators.TransferBetweenDepartmentCalculator;
import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.entities.Transfer;
import ru.maslov.t1.task1.io.printers.TransferPrinter;
import ru.maslov.t1.task1.io.printers.impl.TransferPrinterTxtFilesImpl;
import ru.maslov.t1.task1.io.scanners.EmployeeScanner;
import ru.maslov.t1.task1.io.scanners.impl.EmployeeScannerFromTxtFileImpl;

import java.io.FileNotFoundException;
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
         * Что мне не нравится самому:
         * - улучшить обработку ошибок - сделать ее более осмысленной
         */

        EmployeeScanner scanner =
                new EmployeeScannerFromTxtFileImpl("src\\main\\resources\\test.txt");

        List<Department> departments = new LinkedList<>();
        List<Employee> employees = scanner.scanEmployees(departments);

        System.out.println("Employees");
        System.out.println(employees);
        System.out.println("Departments");
        System.out.println(departments);

        departments
                .forEach(department -> {
                    System.out.println("For department " + department.getName()
                    + " average salary is " + AverageDepartmentSalaryCalculator.calculate(department));
                });

        TransferPrinter printer = new TransferPrinterTxtFilesImpl(
                "src\\main\\resources\\res_test.txt");
        List<Transfer> transfers =
                TransferBetweenDepartmentCalculator.calculate(departments.get(0), departments.get(1));
        printer.printTransfers(transfers);


    }
}
