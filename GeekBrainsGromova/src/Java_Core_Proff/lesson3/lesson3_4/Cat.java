package Java_Core_Proff.lesson3.lesson3_4;

import java.io.Serializable;

public class Cat implements Serializable {
    private String name;
    private String color;

    public Cat(String name, String color){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void info(){
        System.out.println("Котик " + name + " цветом " + color);
    }
}
