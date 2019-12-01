package lesson4;

public class Duck extends Birds{
    public Duck(String name){
        this.name = name;
    }
    public void swim(){
        System.out.println(this.name + " is swiming");
    }
}
