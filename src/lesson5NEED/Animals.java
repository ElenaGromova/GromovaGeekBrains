package lesson5NEED;
import java.util.Scanner;

public class Animals {
    static Scanner scanner = new Scanner(System.in);
    String name;
    int run;
    double jump;
    int swim;

    public Animals() {
    }

    public Animals(String name){
        this.name = name;
    }

    public Animals(String name, int run, int swim, double jump) {
        this.name = name ;
        this.run = run;
        this.swim = swim;
        this.jump = jump;
    }

    String getName(){
        return name;
    }

    void getRun(int maxRun){
        System.out.println((run <= maxRun && run > 0) ? (name + " умеет бегать до " + run + " метров") : (name + " не умеет бегать"));
    }

    void getSwim(int maxSwim){
        System.out.println((swim <= maxSwim && swim > 0) ? (name + " умеет плавать на " + swim + " метров") : (name + " не умеет плавать"));
    }

    void getJump(double maxJump){
        System.out.println((jump <= maxJump && jump > 0) ? (name + " умеет перепрыгивать препятствия до " + jump + " метров") : (name + " не умеет перепрыгивать препятствия"));
    }
}