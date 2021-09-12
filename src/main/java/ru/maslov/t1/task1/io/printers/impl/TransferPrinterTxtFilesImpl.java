package ru.maslov.t1.task1.io.printers.impl;

import ru.maslov.t1.task1.calculators.AverageDepartmentSalaryCalculator;
import ru.maslov.t1.task1.entities.Transfer;
import ru.maslov.t1.task1.io.printers.TransferPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Formatter;
import java.util.List;

/**
 * Файл для вывода информации о  сотрудниках в файл
 */
public class TransferPrinterTxtFilesImpl
        implements TransferPrinter {

    private Formatter formatter;

    private static final String outputPattern = "Перевод сотрудника %-30.25s из отдела " +
            "%-20.18s в отлел %-20.18s изменение ЗП в старом отделе: с %9.2f на %9.2f" +
            " изменение ЗП в новом отделе: с %9.2f на %9.2f" + System.getProperty("line.separator");

    public TransferPrinterTxtFilesImpl(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void printTransfer(Transfer transfer) {
        formatter.format(outputPattern,
                transfer.getEmployee().getName(),
                transfer.getSrcDepartment().getName(),
                transfer.getTargetDepartment().getName(),
                AverageDepartmentSalaryCalculator.calculate(transfer.getSrcDepartment()),
                AverageDepartmentSalaryCalculator
                        .calculateWithoutEmployee(transfer.getSrcDepartment(), transfer.getEmployee()),
                AverageDepartmentSalaryCalculator.calculate(transfer.getTargetDepartment()),
                AverageDepartmentSalaryCalculator
                        .calculateWithEmployee(transfer.getTargetDepartment(), transfer.getEmployee()));
    }
}
