package lesson5;

public class CatEat extends Animals {
    String res;
    int eat;
    int swim = -1;
    int runCat = 200;
    int swimCat = -1;
    int jumpCat = 2;
    boolean full; // сытость

    CatEat(String name, int eat) {
        super(name);
        this.eat = eat;
        this.full = false;
    }

    CatEat(String name, int run, double jump) {
        super("Котик " + name);
        this.run = run;
        this.jump = jump;
    }

    public void catInfo(){
        getRun(runCat);
        getSwim(swimCat);
        getJump(jumpCat);
    }

    public void printCatFull(){
        System.out.println(full ? ("Котик " + name + " наелся") : ("Котик " + name + " остался голодным"));
    }

}
