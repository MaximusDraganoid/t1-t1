package ru.maslov.t1.task1.runners;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.io.scanners.EmployeeScanner;
import ru.maslov.t1.task1.io.scanners.impl.EmployeeScannerFromTxtFileImpl;

import java.io.FileNotFoundException;
import java.util.Map;

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

        EmployeeScanner scanner =
                new EmployeeScannerFromTxtFileImpl("src\\main\\resources\\test.txt",
                        "src\\main\\resources\\errors.txt");
        Map<String, Department> departmentMap = scanner.scanEmployees();
        System.out.println(departmentMap);
    }
}
