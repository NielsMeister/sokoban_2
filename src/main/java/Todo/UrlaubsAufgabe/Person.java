package Todo.UrlaubsAufgabe;

import java.time.LocalDate;
import java.util.Date;

public class Person {

    protected String name;
    protected Date birthDate;

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}
