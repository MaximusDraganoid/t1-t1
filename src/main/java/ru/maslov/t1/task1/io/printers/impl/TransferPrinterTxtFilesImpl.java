package ru.maslov.t1.task1.io.printers.impl;

import ru.maslov.t1.task1.calculators.AverageDepartmentSalaryCalculator;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.entities.GroupTransfer;
import ru.maslov.t1.task1.entities.Transfer;
import ru.maslov.t1.task1.io.printers.TransferPrinter;

import java.util.Formatter;

/**
 * Файл для вывода информации о  сотрудниках в файл
 */
public class TransferPrinterTxtFilesImpl
        implements TransferPrinter {

    private Formatter formatter;

    private static final String outputTransferPattern = "Перевод сотрудника %-30.25s из отдела " +
            "%-20.18s в отлел %-20.18s изменение ЗП в старом отделе: с %9.2f на %9.2f" +
            " изменение ЗП в новом отделе: с %9.2f на %9.2f" + System.getProperty("line.separator");

    public TransferPrinterTxtFilesImpl(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void printTransfer(Transfer transfer) {
        formatter.format(outputTransferPattern,
                transfer.getEmployee().getName(),
                transfer.getSrcDepartment().getName(),
                transfer.getTargetDepartment().getName(),
                AverageDepartmentSalaryCalculator.calculate(transfer.getSrcDepartment()),
                AverageDepartmentSalaryCalculator
                        .calculateWithoutEmployee(transfer.getSrcDepartment(), transfer.getEmployee()),
                AverageDepartmentSalaryCalculator.calculate(transfer.getTargetDepartment()),
                AverageDepartmentSalaryCalculator
                        .calculateWithEmployee(transfer.getTargetDepartment(), transfer.getEmployee()));
        formatter.flush();
    }

    @Override
    public void printGroupTransfers(GroupTransfer transfer) {
        printLine();
        formatter.format("Перевод следующих сотрудников из отдела %-20.18s в отлел %-20.18s"
                        + System.getProperty("line.separator"),
                transfer.getSrc().getName(),
                transfer.getDst().getName());
        for (Employee employee : transfer.getEmployees()) {
            formatter.format("Сотрудник: %-30.25s" + System.getProperty("line.separator"), employee.getName()
            );
        }
        formatter.format("изменения средней ЗП в старом отделе с с %9.2f на %9.2f" + System.getProperty("line.separator"),
                AverageDepartmentSalaryCalculator.calculate(transfer.getSrc()),
                AverageDepartmentSalaryCalculator
                        .calculateWithoutEmployees(transfer.getSrc(), transfer.getEmployees()));
        formatter.format("изменения средней ЗП в новом отделе с с %9.2f на %9.2f" + System.getProperty("line.separator"),
                AverageDepartmentSalaryCalculator.calculate(transfer.getDst()),
                AverageDepartmentSalaryCalculator
                        .calculateWithEmployees(transfer.getDst(), transfer.getEmployees()));
        printLine();
        formatter.flush();
    }

    private void printLine() {
        String line = "";
        for (int i = 0; i < 90; i++) {
            line += "-";
        }
        formatter.format(line + System.getProperty("line.separator"));
    }
}
