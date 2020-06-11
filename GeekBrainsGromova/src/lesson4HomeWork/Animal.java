package lesson4HomeWork;

import java.util.Scanner;

public class Animal {
    protected static Scanner scanner = new Scanner(System.in);
    protected String name;
    protected String type;
    protected int run;
    protected double jump;
    protected int swim;
    protected final int runCat = 200;
    protected final int runDog1 = 600;
    protected final int runDog2 = 400;
    protected final int swimCat = -1;
    protected final int swimDog = 10;
    protected final int jumpCat = 2;
    protected final double jumpDog = 0.5;

    public Animal() {
    }

    public Animal(String name, int run, int swim, double jump) {
        this.name = name ;
        this.type = type;
        this.run = run;
        this.swim = swim;
        this.jump = jump;
    }
    public void animalInfo (int maxRun, int maxSwim, double maxJump) {
        System.out.println ( type + ": " + name);
        System.out.println(getRun(maxRun) ? (name + " умеет бегать до " + run + " метров") : name + " не умеет бегать");
        System.out.println(getSwim(maxSwim) ? (name + " умеет плавать на " + swim + " метров") : name + " не умеет плавать");
        System.out.println(getJump(maxJump) ? (name + " умеет перепрыгивать препятствия до " + jump + " метров") : name + " не умеет перепрыгивать препятствия");
        System.out.println("-------------------------------------------------------");
    }

    public boolean getRun(int maxRun){
        return (run <= maxRun && run > 0);
    }

    public boolean getSwim(int maxSwim){
        return (swim <= maxSwim && swim > 0);
    }

    public boolean getJump(double maxJump){
        return (jump <= maxJump && jump > 0);
    }
}