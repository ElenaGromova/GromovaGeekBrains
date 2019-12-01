package lesson4HomeWork;

public class Employee {
    protected String fio;
    protected String position;
    protected String email;
    protected String telefone;
    protected int zp;
    protected int age;

    public Employee(){

    }

    public Employee(String fio, String position, String email, String telefone, int zp, int age){
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.telefone = telefone;
        this.zp = zp;
        this.age = age;
    }

    public void employeeInfo(){
        System.out.println("ФИО сотрудника " + fio);
        System.out.println("Должность сотрудника " + position);
        System.out.println("Email сотрудника " + email);
        System.out.println("Телефон сотрудника " + telefone);
        System.out.println("Зарплата сотрудника " + zp);
        System.out.println("Возраст сотрудника " + age);
    }

}
