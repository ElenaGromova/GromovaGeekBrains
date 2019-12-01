package lesson4HomeWorkNeed;
import java.util.Scanner;

public class Animal {
    protected String name;

    protected int runLimit = 0;
    protected double jumpLimit = 0;
    protected int swimLimit = 0;

    protected final int runCat = 200;
    protected final int runDog1 = 600;
    protected final int runDog2 = 400;
    protected final int swimCat = -1;
    protected final int swimDog = 10;
    protected final int jumpCat = 2;
    protected final double jumpDog = 0.5;

    public Animal() {
        this.name = "Безымянный";
    }

    public Animal(String name){
        this.name = name;
    }

    public void run(int distance){
        if (runLimit > 0 && distance <= runLimit){
            System.out.println(this.name + " run " + distance + " metres");
        } else {
            System.out.println(this.name + " cant run such");
        }
    }

    public void swim(int distance){
        if (swimLimit > 0 && distance <= swimLimit){
            System.out.println(this.name + " swim " + distance + " metres");
        } else {
            System.out.println(this.name + " cant swim such");
        }
    }

    public void jump(double distance){
        if (jumpLimit > 0 && distance <= jumpLimit){
            System.out.println(this.name + " jump " + distance + " metres");
        } else {
            System.out.println(this.name + " cant jump such");
        }
    }


}