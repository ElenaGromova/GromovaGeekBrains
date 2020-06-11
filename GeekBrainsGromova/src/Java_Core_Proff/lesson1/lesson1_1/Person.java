package Java_Core_Proff.lesson1.lesson1_1;

public class Person {
    private String name;

    public Person(String name){
        this.name=name;
    }

    @Override
    public String toString(){
        return "Person " + name;
    }
}
