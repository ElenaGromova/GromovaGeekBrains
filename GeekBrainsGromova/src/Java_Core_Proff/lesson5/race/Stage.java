package Java_Core_Proff.lesson5.race;

import Java_Core_Proff.lesson5.race.Car;

public abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}
