package Todo.UrlaubsAufgabe;

public class Employee extends Person{

    protected double salary;

    public void raiseSalary(double factor){
        salary *= factor;
    }
}
