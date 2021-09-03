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

    public static List<Transfer> calculate(Department department1, Department department2) {
        List<Transfer> resultList = new LinkedList<>();

        resultList.addAll(calculateTransfersFromSrcDepartmentToDst(department1, department2));
        resultList.addAll(calculateTransfersFromSrcDepartmentToDst(department2, department1));

        return resultList;
    }

    private static List<Transfer> calculateTransfersFromSrcDepartmentToDst(Department dst,
                                                                           Department src) {
        List<Transfer> resultList = new LinkedList<>();
        BigDecimal averageSalaryInDep1 = AverageDepartmentSalaryCalculator.calculate(dst);
        BigDecimal averageSalaryInDep2 = AverageDepartmentSalaryCalculator.calculate(src);

        List<Employee> srsList = new LinkedList<>(src.getEmployees());

        for(Employee employee : srsList) {
            dst.getEmployees().add(employee);
            src.getEmployees().remove(employee);

            if (src.getEmployees().size() != 0) {
                BigDecimal newAverageSalaryInDep1 =
                        AverageDepartmentSalaryCalculator.calculate(dst);
                BigDecimal newAverageSalaryInDep2
                        = AverageDepartmentSalaryCalculator.calculate(src);

                if (newAverageSalaryInDep1.compareTo(averageSalaryInDep1) > 0
                        && newAverageSalaryInDep2.compareTo(averageSalaryInDep2) > 0) {
                    resultList.add(new Transfer(employee, dst));
                }
            }
            dst.getEmployees().remove(employee);
            src.getEmployees().add(employee);
        }

        return resultList;
    }

}
