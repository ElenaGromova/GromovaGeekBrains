package lesson4;

public class MainClass {
    public static void main(String[] arg){
        Animal animal = new Animal("Animals");
        Cat cat = new Cat("Barsik", 4, "White");
        Duck duck = new Duck("Donald");
        animal.animalInfo();
        cat.animalInfo();
        cat.catInfo();
        duck.fly();
        duck.swim();
        duck.animalInfo();
    }
}
