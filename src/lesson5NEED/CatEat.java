package lesson5NEED;

public class CatEat extends Animals {
    String res;
    int appetite;
    int swim = -1;
    int runLimit = 200;
    int swimCat = -1;
    int jumpLimit = 2;
    boolean isHungry = true; // сытость

    public CatEat(String name, int appetite){
        super("CatEat " + name);
        this.runLimit = 200;
        this.jumpLimit = 2;
        this.appetite = appetite;
    }

    public void swim(int distance){
        System.out.println("CatEat " + name + " dont swiming");
    }

    CatEat(String name, int run, double jump) {
        super("Котик " + name);
        this.run = run;
        this.jump = jump;
    }

    public void catInfo(){
        getRun(runLimit);
        getSwim(swimCat);
        getJump(jumpLimit);
    }

    public void eat(Miska miska){
        if (miska.checkFoodAmount(this.appetite)){
            miska.decreaseFoodAmojnt(this.appetite);
            System.out.println(this.getName() + "ест из миски с аппетитом" + this.appetite);
            isHungry = false;
        }
        else{
            System.out.println("В миске кончилась еда");
            miska.putSomeFood();
            eat(miska);
        }
    }

    public boolean isCatHungry(){return isHungry;}

    public void printCatFull(){
        System.out.println(isHungry ? ("Котик " + name + " наелся") : ("Котик " + name + " остался голодным"));
    }

}
