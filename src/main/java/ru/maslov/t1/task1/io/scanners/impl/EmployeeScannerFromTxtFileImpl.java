package ru.maslov.t1.task1.io.scanners.impl;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.exceptions.EmployeeValidationException;
import ru.maslov.t1.task1.io.scanners.EmployeeScanner;
import ru.maslov.t1.task1.validators.EmployeeValidator;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Релизация считывателя сотрудников, основнанная на считывании из файла
 */
public class EmployeeScannerFromTxtFileImpl
        implements EmployeeScanner {
    /**
     * Имя файла, из которого считываются данные
     */
    private final String txtFilePath;
    /**
     * Имя файла, в который записываются данные
     */
    private final String errorsFilePath;

    public EmployeeScannerFromTxtFileImpl(String txtFilePath, String errorsFilePath) {
        this.txtFilePath = txtFilePath;
        this.errorsFilePath = errorsFilePath;
    }

    @Override
    public Map<String, Department> scanEmployees() {
        Map<String, Department> departments = new HashMap<String, Department>();

        int rowCount = 0;
        try(BufferedReader br =
                    new BufferedReader(new FileReader(txtFilePath));
            BufferedWriter readRecordsWithErrorsOutputFile
                = new BufferedWriter(new FileWriter(errorsFilePath));
            ) {
            String employeeInfo;
            while ((employeeInfo = br.readLine()) != null) {
                rowCount++;
                String[] empData = employeeInfo.split(";");

                try {
                    EmployeeValidator.validateEmployeeData(empData);

                    departments.putIfAbsent(empData[2],
                            new Department(new LinkedList<>(), empData[2]));
                    departments.get(empData[2])
                            .getEmployees()
                            .add(new Employee(empData[0], new BigDecimal(empData[1])));

                } catch (EmployeeValidationException e) {
                    readRecordsWithErrorsOutputFile.write("Ошибка при чтении из файла " +
                            txtFilePath + " в строке №" + rowCount + " : " +
                            e.getMessage() + ". Содержимое строки: ");
                    readRecordsWithErrorsOutputFile.write(employeeInfo);
                    readRecordsWithErrorsOutputFile.write(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникли проблемы при считывании данных из входного " +
                    "файла или при записи данных в файл об ошибке считывания. Убедитесь, что " +
                    "переданы корректные файлы", e);
        }
        return departments;
    }


}
