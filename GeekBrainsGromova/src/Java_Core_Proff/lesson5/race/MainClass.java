package Java_Core_Proff.lesson5.race;

import Java_Core_Proff.lesson5.race.Car;
import Java_Core_Proff.lesson5.race.Race;
import Java_Core_Proff.lesson5.race.Road;
import Java_Core_Proff.lesson5.race.Tunnel;

import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static int countRases = 0;
    public static final CountDownLatch START = new CountDownLatch(CARS_COUNT+1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];


        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
            Thread.sleep((int) (Math.random() * 500));
        }

        while (START.getCount() > 1) {
            Thread.sleep(100);
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        START.countDown();

        while (START.getCount() == 0) {
            countRases = 0;
            for (int i = 0; i < cars.length; i++) {
                if (cars[i].getCount() == race.getStages().size()) {
                    countRases++;
                }
                else {
                    break;
                }
            }
            if (countRases == CARS_COUNT) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                break;
            }
        }
    }
}


