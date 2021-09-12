package ru.maslov.t1.task1.io.printers;

import ru.maslov.t1.task1.entities.GroupTransfer;
import ru.maslov.t1.task1.entities.Transfer;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для функциональности вывода информации в файл
 */
public interface TransferPrinter {
    void printTransfer(Transfer employees) throws IOException;
    void printGroupTransfers(GroupTransfer transfer);

}
