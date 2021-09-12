package ru.maslov.t1.task1.calculators;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.entities.GroupTransfer;
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

    private final TransferPrinter transferPrinter;
    private BigDecimal srcDepartmentAverageSalary;
    private BigDecimal dstDepartmentAverageSalary;

    public TransferBetweenDepartmentCalculator(TransferPrinter transferPrinter) {
        this.transferPrinter = transferPrinter;
        srcDepartmentAverageSalary = new BigDecimal(0);
        dstDepartmentAverageSalary = new BigDecimal(0);
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

    public void calculateGroupTransfersBetweenDepartments (Department src, Department dst) {
        List<Employee> employees = new LinkedList<>();
        srcDepartmentAverageSalary = AverageDepartmentSalaryCalculator.calculate(src);
        dstDepartmentAverageSalary = AverageDepartmentSalaryCalculator.calculate(dst);
        recursionCalculating(src, dst, employees, 0, src.getEmployees().size());
    }

    private void recursionCalculating(Department src, Department dst, List<Employee> employees, int recursionInitializer, int maxRecursionDeep) {
        if (recursionInitializer == maxRecursionDeep) {
            //начало вычислений
            BigDecimal currentSrcDepartmentAverageSalary = AverageDepartmentSalaryCalculator.calculateWithoutEmployees(src, employees);
            BigDecimal currentDstDepartmentAverageSalary = AverageDepartmentSalaryCalculator.calculateWithEmployees(dst, employees);
            if (currentDstDepartmentAverageSalary.compareTo(dstDepartmentAverageSalary) > 0
                && currentSrcDepartmentAverageSalary.compareTo(srcDepartmentAverageSalary) > 0) {
                //сохранение результата вычислений в файл
                transferPrinter.printGroupTransfers(new GroupTransfer(src, dst, employees));
            }
        } else {
            Employee employee = src.getEmployees().get(recursionInitializer);
            employees.add(employee);
            recursionCalculating(src, dst, employees, recursionInitializer + 1, maxRecursionDeep);
            employees.remove(employee);
            recursionCalculating(src, dst, employees, recursionInitializer + 1, maxRecursionDeep);
        }
    }
}
