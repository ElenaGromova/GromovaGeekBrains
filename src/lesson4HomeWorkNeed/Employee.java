package lesson4HomeWorkNeed;

public class Employee {
    private String name;
    private String position;
    private String email;
    private String phone;
    private int age;

    public Employee(String name, String position, String email, String phone, int age){
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public void printEmployee(){
        System.out.println(this.email + ": " + name);
    }

    public String toString(){
        return this.name + " " + this.position;
    }

    public int getAge(){return this.age;}

}
