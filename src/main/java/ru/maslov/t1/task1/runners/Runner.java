package ru.maslov.t1.task1.runners;

import ru.maslov.t1.task1.calculators.AverageDepartmentSalaryCalculator;
import ru.maslov.t1.task1.calculators.TransferBetweenDepartmentCalculator;
import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.entities.Transfer;
import ru.maslov.t1.task1.printers.TransferPrinter;
import ru.maslov.t1.task1.printers.impl.TransferPrinterTxtFilesImpl;
import ru.maslov.t1.task1.scanners.EmployeeScanner;
import ru.maslov.t1.task1.scanners.impl.EmployeeScannerFromTxtFileImpl;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
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
         * - разобраться с вопросом считывания из файла и почему приходится передавать абсолютный путь и
         * почему не работает просто передача названия файла? В конце концов, файл то находится в папке с
         * ресурсами и по идее должен быть виден
         * - улучшить обработку ошибок - сделать ее более осмысленной
         */

        EmployeeScanner scanner =
                new EmployeeScannerFromTxtFileImpl("C:\\Users\\mmaslov\\IdeaProjects" +
                        "\\t1-t1\\src\\main\\resources\\test.txt");

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

        TransferPrinter printer = new TransferPrinterTxtFilesImpl("C:\\Users\\mmaslov\\IdeaProjects" +
                "\\t1-t1\\src\\main\\resources\\res_test.txt");
        List<Transfer> transfers =
                TransferBetweenDepartmentCalculator.calculate(departments.get(0), departments.get(1));
        printer.printTransfers(transfers);


    }
}
