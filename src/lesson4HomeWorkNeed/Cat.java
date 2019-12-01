package lesson4HomeWorkNeed;

public class Cat extends Animal {

    public Cat(String name){
        super("CatEat " + name);
        this.runLimit = 200;
        this.jumpLimit = 2;
    }

    public void swim(int distance){
        System.out.println("CatEat " + name + " dont swiming");
    }



}
