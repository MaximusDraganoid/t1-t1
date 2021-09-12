package ru.maslov.t1.task1.runners;

import ru.maslov.t1.task1.calculators.TransferBetweenDepartmentCalculator;
import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.io.printers.DepartmentInfoPrinter;
import ru.maslov.t1.task1.io.printers.TransferPrinter;
import ru.maslov.t1.task1.io.printers.impl.ConsoleDepartmentInfoPrinterImpl;
import ru.maslov.t1.task1.io.printers.impl.TransferPrinterTxtFilesImpl;
import ru.maslov.t1.task1.io.scanners.EmployeeScanner;
import ru.maslov.t1.task1.io.scanners.impl.EmployeeScannerFromTxtFileImpl;

import java.io.*;
import java.util.Formatter;
import java.util.Map;

public class Runner {

    private final static int NUM_OF_STARS = 120;
    /**
     * "src\\main\\resources\\test.txt"
     * "src\\main\\resources\\errors.txt"
     * "src\\main\\resources\\res_test.txt"
     */
    public static void main(String[] args) throws FileNotFoundException {
        /**
         * Написать программу, которая читает из файла информацию о
         * сотрудниках и их принадлежности к отделам, рассчитывает
         * среднюю зарплату сотрудников в отделе, строит и выводит в
         * файл все варианты возможных переводов сотрудников из одного
         * отдела в другой, при которых средняя зарплата отдела увеличивается
         * в обоих отделах.
         */

        fileValidation(args);

//        try {
//            prepareOutputFile(args[2]);
//        } catch (IOException e) {
//            throw new RuntimeException("Возникла ошибка при очистке выходного файла", e);
//        }

        EmployeeScanner scanner =
                new EmployeeScannerFromTxtFileImpl(args[0], args[1]);
        Map<String, Department> departmentMap = scanner.scanEmployees();

        DepartmentInfoPrinter departmentPrinter =
                new ConsoleDepartmentInfoPrinterImpl();

        printStarsLine();
        System.out.println("Выводим информацию о департаментах");
        for(String depName : departmentMap.keySet()) {
            departmentPrinter.printMainDepartmentInfo(departmentMap.get(depName));
        }
        System.out.println();

        printStarsLine();
        System.out.println("Выводим информацию о сотдрудниках департаментов");
        for(String depName : departmentMap.keySet()) {
            departmentPrinter.printEmployeeInfo(departmentMap.get(depName));
        }
        printStarsLine();
        System.out.println();

//        System.out.println();
//        System.out.println("Начинаем вычисления переводов между департаменами");
//        calculateSingleTransfers(departmentMap, args[2]);
//        printStarsLine();

        System.out.println();
        System.out.println("Начинаем вычисления групповых переводов между департаменами");
        calculateGroupTransfers(departmentMap, args[2]);
        printStarsLine();
        System.out.println("Вычисления окончены! реузльтат записан в " + args[2]);
    }

    private static void calculateGroupTransfers(Map<String, Department> departmentMap,
                                                 String resultFilPath) {
        try (Formatter formatter =
                     new Formatter(resultFilPath)) {

            TransferPrinter transferPrinter
                    = new TransferPrinterTxtFilesImpl(formatter);

            TransferBetweenDepartmentCalculator transferCalculator =
                    new TransferBetweenDepartmentCalculator(transferPrinter);

            for (String srcDepName : departmentMap.keySet()) {
                for (String dstDepName : departmentMap.keySet()) {
                    if (!dstDepName.equals(srcDepName)) {
                        transferCalculator
                                .calculateGroupTransfersBetweenDepartments(departmentMap.get(srcDepName), departmentMap.get(dstDepName));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли проблемы с записью результатов в файл", e);
        }
    }

    private static void calculateSingleTransfers(Map<String, Department> departmentMap,
                                                 String resultFilPath) {
        try (Formatter formatter =
                     new Formatter(new BufferedWriter(new FileWriter(resultFilPath)))) {

            TransferPrinter transferPrinter
                    = new TransferPrinterTxtFilesImpl(formatter);

            TransferBetweenDepartmentCalculator transferCalculator =
                    new TransferBetweenDepartmentCalculator(transferPrinter);

            for (String srcDepName : departmentMap.keySet()) {
                for (String dstDepName : departmentMap.keySet()) {
                    if (!dstDepName.equals(srcDepName)) {
                        transferCalculator
                                .calculate(departmentMap.get(srcDepName), departmentMap.get(dstDepName));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли проблемы с записью результатов в файл", e);
        }
    }

    private static void fileValidation(String[] args) {

        if (args.length < 3) {
            throw new RuntimeException("Передано слишком мало аргументов дл работы программы!");
        }

        if (args.length > 3) {
            throw new RuntimeException("Передано слишком много аргументов для работы программы!");
        }

        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            throw new RuntimeException("Указанного файла с входными данными не существует!");
        }

        if (!inputFile.isFile()) {
            throw new RuntimeException("Указанный файл с входными данными не является файлом!");
        }

        File errorsFile = new File(args[1]);
        try {
            if (!errorsFile.exists()) {
                errorsFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли приблемы при создани файла для записи ошибок", e);
        }
        if (!errorsFile.isFile()) {
            throw new RuntimeException("Указанный файл для вывода ошибок не является файлом!");
        }

        File outputFile = new File(args[2]);
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли приблемы при создани файла для записи результата", e);
        }
        if (!inputFile.isFile()) {
            throw new RuntimeException("Указанный файл для вывода результатов работы программы" +
                    " не является файлом!");
        }
    }

    private static void prepareOutputFile(String fileName) throws IOException {
        new FileWriter(fileName, false).close();
    }

    private static void printStarsLine() {
        for (int i = 0; i < NUM_OF_STARS; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

}
