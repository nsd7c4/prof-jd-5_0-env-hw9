package Service;

import data.Employee;
import exceptions.EmployeeNotFound;
import exceptions.ExistedEmployee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private HashMap<String, Employee> book = new HashMap<>(Map.of(
            "R G", new Employee("R","G", 1, 13230d),
            "R G2", new Employee("R", "G2", 1, 999d),
            "R G3", new Employee("R", "G3", 1, 888d),
            "R G4", new Employee("R", "G4", 2, 55d),
            "R G5", new Employee("R", "G5", 3, 666d),
            "R G6", new Employee("R", "G6", 6, 555d)
            ));


    public String addEmployee(String firstName, String lastName) throws ExistedEmployee {
        boolean full = true;
        String result = new String();
        Employee e = new Employee(firstName, lastName, Integer.MAX_VALUE, Double.MAX_VALUE);
        if (book.containsKey(e.getFullName())) {
            throw new ExistedEmployee();
        } else {
            book.put(e.getFullName(), e);
            result = e.toString();
        }

        return result;
    }


    public String removeEmployee(String firstName, String lastName) throws EmployeeNotFound {
        String result = null;
        Employee e = new Employee(firstName, lastName, Integer.MAX_VALUE, Double.MAX_VALUE);
        if (book.containsKey(e.getFullName())) {
            book.remove(e.getFullName());
            result = e.toString();
        }


        if (result == null) {
            throw new EmployeeNotFound();
        }


        return result;
    }


    public String findEmployee(String firstName, String lastName) throws EmployeeNotFound {
        String result = null;
        Employee e = new Employee(firstName, lastName, Integer.MAX_VALUE, Double.MAX_VALUE);
        if (book.containsKey(e.getFullName())) {
            result = e.toString();
        }


        if (result == null) {
            throw new EmployeeNotFound();
        }
        return result;
    }

    public String allEmployee() {
        String result = book.toString();
        return result.toString();
    }

    public String maxSalaryByDepartmentn(int departmentID) {
        final Employee streamMax = book.values().stream()
                .filter(employee -> employee.getDepartment()==departmentID)
                .max((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))
                .orElseThrow();
        return  streamMax.toString();
    }

    public String minSalaryByDepartmentn(int departmentID) {
        final Employee streamMax = book.values().stream()
                .filter(employee -> employee.getDepartment()==departmentID)
                .min((e1,e2) -> Double.compare(e1.getSalary(),e2.getSalary()))
                .orElseThrow();
        return  streamMax.toString();
    }

    public String allByDepartment(int departmentID) {
        final List<Employee> streamAll= book.values().stream()
                .filter(employee -> employee.getDepartment()==departmentID)
                .collect(Collectors.toList());
        return streamAll.toString();
    }

}
