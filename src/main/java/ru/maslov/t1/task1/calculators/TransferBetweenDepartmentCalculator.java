package ru.maslov.t1.task1.calculators;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.entities.Transfer;
import ru.maslov.t1.task1.io.printers.TransferPrinter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для вычисления комбинаций откуда и куда может уйти сотрудник
 * с целью повышения средней ЗП в отделе
 */
public class TransferBetweenDepartmentCalculator {

    private TransferPrinter transferPrinter;

    public TransferBetweenDepartmentCalculator(TransferPrinter transferPrinter) {
        this.transferPrinter = transferPrinter;
    }

    /**
     * Вычисление прееводов из одого отдела в другой
     */
    public void calculate(Department department1, Department department2) throws IOException{
        calculateTransfersFromSrcDepartmentToDst(department1, department2);
        calculateTransfersFromSrcDepartmentToDst(department2, department1);
    }

    private void calculateTransfersFromSrcDepartmentToDst(Department dst, Department src) throws IOException {
        BigDecimal dstAverageSalary = AverageDepartmentSalaryCalculator.calculate(dst);
        BigDecimal srcAverageSalary = AverageDepartmentSalaryCalculator.calculate(src);

        for(Employee employee : src.getEmployees()) {
            if (employee.getSalary().compareTo(dstAverageSalary) > 0
                && employee.getSalary().compareTo(srcAverageSalary) < 0) {

                transferPrinter.printTransfer(new Transfer(employee, src, dst));
            }
        }
    }

}
