package ru.maslov.t1.task1.printers.impl;

import ru.maslov.t1.task1.entities.Transfer;
import ru.maslov.t1.task1.printers.TransferPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Файл для вывода информации о  сотрудниках в файл
 */
public class TransferPrinterTxtFilesImpl
        implements TransferPrinter {

    private String txtFilePath;

    public TransferPrinterTxtFilesImpl(String txtFilePath) {
        this.txtFilePath = txtFilePath;
    }

    @Override
    public void printTransfers(List<Transfer> transfers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(txtFilePath))) {
            for (Transfer transfer : transfers) {
                writeEmployeeToFile(transfer, bw);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для записи информации об 1 сотруднике в файл
     * @param transfer - совершаемый трансфер
     * @param bufferedWriter - поток, в который пишем
     * @throws IOException
     */
    private void writeEmployeeToFile(Transfer transfer, BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(transfer.getEmployee().getName() + " from "
                + transfer.getEmployee().getDepartment().getName() + " to "
                + transfer.getTargetDepartment().getName());
        bufferedWriter.newLine();
    }
}
