package Java_Core_Proff.lesson5.race;

import static Java_Core_Proff.lesson5.race.MainClass.START;
//import static Java_Core_Proff.lesson5.race.MainClass.START;

public class Car<Count> implements Runnable {
    private static int CARS_COUNT;
    //public static final CountDownLatch START1 = new CountDownLatch(4);
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private int count;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) { this.count = count;}
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.count = 0;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(1000);
            System.out.println(this.name + " готов");
            START.countDown();
            START.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

    }
}
