package ru.maslov.t1.task1.calculators;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;
import ru.maslov.t1.task1.entities.Transfer;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для вычисления комбинаций откуда и куда может уйти сотрудник
 * с целью повышения средней ЗП в отделе
 */
public class TransferBetweenDepartmentCalculator {

    private TransferBetweenDepartmentCalculator() {}

    /**
     * Вычисление прееводов из одого отдела в другой
     */
    public static List<Transfer> calculate(Department department1, Department department2) {
        List<Transfer> resultList = new LinkedList<>();

        resultList.addAll(calculateTransfersFromSrcDepartmentToDst(department1, department2));
        resultList.addAll(calculateTransfersFromSrcDepartmentToDst(department2, department1));

        return resultList;
    }

    private static List<Transfer> calculateTransfersFromSrcDepartmentToDst(Department dst,
                                                                           Department src) {
        List<Transfer> resultList = new LinkedList<>();

        BigDecimal dstAverageSalary = AverageDepartmentSalaryCalculator.calculate(dst);
        BigDecimal srcAverageSalary = AverageDepartmentSalaryCalculator.calculate(src);

        BigDecimal summarySrcSalary = AverageDepartmentSalaryCalculator.calculateSalariesSum(src);
        BigDecimal summaryDstSalary = AverageDepartmentSalaryCalculator.calculateSalariesSum(dst);

        for(Employee employee : src.getEmployees()) {
            if (employee.getSalary().compareTo(dstAverageSalary) > 0
                && employee.getSalary().compareTo(srcAverageSalary) < 0) {

                BigDecimal newAverageSalaryInDst =
                        AverageDepartmentSalaryCalculator.calculateWithEmployee(dst.getEmployees().size(),
                                employee, summaryDstSalary);

                BigDecimal newAverageSalaryInSrc
                        = AverageDepartmentSalaryCalculator.calculateWithoutEmployee(src.getEmployees().size(),
                                employee, summarySrcSalary);

                resultList.add(new Transfer(employee,
                        src,
                        dst,
                        srcAverageSalary,
                        newAverageSalaryInSrc,
                        dstAverageSalary,
                        newAverageSalaryInDst));
            }
        }

        return resultList;
    }

}
