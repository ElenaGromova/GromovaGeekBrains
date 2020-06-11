package Java_Core_Proff.lesson1.lesson1_3;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> boxOfFruit;
    String name;
    double maxWeigth; //сколько коробка может вместить веса максимально

    Box(ArrayList<T> boxOfFruit, String name, double maxWeigth){
        this.name = name;
        this.boxOfFruit = boxOfFruit;
        this.maxWeigth = maxWeigth;
    }

    public String getName() {
        return name;
    }

    //сколько коробки заполнено уже
    double getWeigth(){
        double boxWeigth = 0.0;
        if (!boxOfFruit.isEmpty()) {
            for (T f : boxOfFruit){
                boxWeigth += f.getWeigth();
            }
        }
        return boxWeigth;
    }

    //сколько коробки еще не заполнено
    double getEmptyWeigth(){
        return (this.maxWeigth - this.getWeigth());
    }

    boolean compare(Box box){
        return (this.getWeigth() == box.getWeigth());
    }

    boolean checkFruitByFruit(T fruit){
        return (this.name == fruit.getName());
    }

    public void putSomeFruit(T fruit){
        if (checkFruitByFruit(fruit)){
            this.boxOfFruit.add(fruit);
        } else {
            System.out.println("Это коробка с другими фруктами! Положите " + fruit.getName() + " другую коробку!");
        }
    }

    public void putIntoAnotherBoxFruit(Box box){
        if ((this.name == box.getName()) && (box.getEmptyWeigth() >= this.getWeigth())){
            box.boxOfFruit.addAll(this.boxOfFruit);
            this.boxOfFruit.clear();
        }
        else {
            System.out.println("Фрукты из этой коробки не могут быть добавлены в выбранную коробку! Пресыпьте фрукты в другую коробку!");
        }
    }

    public void info(){
        System.out.println("Коробка с " + this.name);
        System.out.println("Вес коробки с фруктами: " + this.getWeigth());
        System.out.println("В коробку можно добавить еще " + this.getEmptyWeigth());
        System.out.println("========================================================");
    }

}
