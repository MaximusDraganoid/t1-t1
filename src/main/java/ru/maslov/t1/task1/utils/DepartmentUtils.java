package ru.maslov.t1.task1.utils;

import ru.maslov.t1.task1.entities.Department;
import ru.maslov.t1.task1.entities.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Класс для утилитарных функций для работы с департаментами
 */
public class DepartmentUtils {

    private DepartmentUtils() {}

    /**
     * Метод для поиска департамерта в списке департаментов по имени.
     * Если департамента с таким именем нет - соответствующий
     * департамент создается и добавляется к списку департаментов.
     * @param departments - список департаментов
     * @param departmentName - название искомого депаратмента
     * @return
     */
    public static Department findDepartmentByName(List<Department> departments,
                                                String departmentName) {
        Optional<Department> optionalDepartment = departments
                .stream()
                .filter(department -> department.getName().equals(departmentName))
                .findAny();

        Department department;

        if (optionalDepartment.isPresent()) {
            department = optionalDepartment.get();
        } else {
            department = new Department(new LinkedList<Employee>(), departmentName);
            departments.add(department);
        }

        return department;
    }

    /**
     * Метод для добавления сотрудника в департамент
     * @param employee - целевой сотрудник
     * @param department - целевой департамент
     */
    public static void addEmployeeToDepartment(Employee employee, Department department) {
        department.getEmployees().add(employee);
    }

}
