package Todo.UrlaubsAufgabe;

import java.util.ArrayList;

public class Company {

    ArrayList<Employee> employees;

    public String printOrgChart() {
        return employees.toString();
    }

    public void raiseSalary(double factor) {
        for (Employee employee : employees) {
            employee.raiseSalary(factor);
        }
    }
}
