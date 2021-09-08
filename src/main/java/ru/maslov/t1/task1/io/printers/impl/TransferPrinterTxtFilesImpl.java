package ru.maslov.t1.task1.io.printers.impl;

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

    private String txtFilePath;

    private static final String outputPattern = "Перевод сотрудника %-30.25s из отдела " +
            "%-20.18s в отлел %-20.18s изменение ЗП в старом отделе: с %9.2f на %9.2f" +
            "изменение ЗП в новом отделе: с %9.2f на %9.2f" + System.getProperty("line.separator");

    public TransferPrinterTxtFilesImpl(String txtFilePath) {
        this.txtFilePath = txtFilePath;
        File file = new File(txtFilePath);
    }

    @Override
    public void printTransfers(List<Transfer> transfers) {
        try (Formatter formatter
                     = new Formatter(new BufferedWriter(new FileWriter(txtFilePath, true)))) {
            for (Transfer transfer : transfers) {
                writeEmployeeToFile(transfer, formatter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для записи информации об 1 сотруднике в файл
     * @param transfer - совершаемый трансфер
     * @param formatter - поток, в который пишем
     * @throws IOException
     */
    private void writeEmployeeToFile(Transfer transfer, Formatter formatter) throws IOException {
        formatter.format(outputPattern,
                transfer.getEmployee().getName(),
                transfer.getSrcDepartment().getName(),
                transfer.getTargetDepartment().getName(),
                transfer.getSrcPrevAverageSalary(),
                transfer.getSrcNewAverageSalary(),
                transfer.getTargetPrevAverageSalary(),
                transfer.getTargetNewAverageSalary());
    }
}
