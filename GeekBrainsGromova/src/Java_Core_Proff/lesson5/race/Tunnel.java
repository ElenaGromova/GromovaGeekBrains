package Java_Core_Proff.lesson5.race;

import Java_Core_Proff.lesson5.race.Car;
import Java_Core_Proff.lesson5.race.Stage;

import java.util.concurrent.Semaphore;

import static Java_Core_Proff.lesson5.race.MainClass.CARS_COUNT;

public class Tunnel extends Stage {
    private static final Semaphore SEMAPHORE = new Semaphore((int) CARS_COUNT/2);
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                SEMAPHORE.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                c.setCount(c.getCount() + 1);
                SEMAPHORE.release();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
