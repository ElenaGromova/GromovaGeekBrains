package lesson4HomeWorkNeed;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] arrayOfEmployees = new Employee[5];
        arrayOfEmployees[0] = new Employee("Ivanov Ivan", "Engineer", " ivivan@mailbox.com ", "892312312", 30);
        arrayOfEmployees[1] = new Employee("Petrov Petr", "Driver", " petrov@mailbox.com ", "89507777777", 21);
        arrayOfEmployees[2] = new Employee("Sidorov Roman", "Developer", " sidorov@mailbox.com ", "89508888888", 41);
        arrayOfEmployees[3] = new Employee("Ivanova Anna", "Manager", " ivanovaa@mailbox.com ", "89505555555", 42);
        arrayOfEmployees[4] = new Employee("Petrova Sveta", "Operator", " petrova@mailbox.com ", "89504444444", 22);

        System.out.println(arrayOfEmployees[0]);
        arrayOfEmployees[0].printEmployee();

        for (Employee employee : arrayOfEmployees) {
            if (employee.getAge() > 40) {
                employee.printEmployee();
            }
        }
    }
}

