package ru.maslov.t1.task1.printer.impl;

import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.printer.EmployeePrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Файл для вывода информации о  сотрудниках в файл
 */
public class EmployeePrinterTxtFilesImpl
        implements EmployeePrinter {

    private String txtFilePath;

    public EmployeePrinterTxtFilesImpl(String txtFilePath) {
        this.txtFilePath = txtFilePath;
    }

    @Override
    public void printEmployees(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtFilePath))) {
            for (Employee employee : employees) {
                writeEmployeeToFile(employee, bw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для записи информации об 1 сотруднике в файл
     * @param employee - сотрудник
     * @param bufferedWriter - поток, в который пишем
     * @throws IOException
     */
    private void writeEmployeeToFile(Employee employee, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(employee.getName() + " "
                + employee.getSalary() + " "
                + employee.getDepartment().getName());
        bufferedWriter.newLine();
    }

}
