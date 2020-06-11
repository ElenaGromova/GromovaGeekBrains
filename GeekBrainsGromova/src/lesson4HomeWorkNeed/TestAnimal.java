package lesson4HomeWorkNeed;

public class TestAnimal {
    public static void main(String[] args){
        Animal[] arrayOfAnimals = new Animal[3];

        arrayOfAnimals[0] = new Cat("Bars");
        arrayOfAnimals[1] = new Dog("Chapa");
        arrayOfAnimals[2] = new SuperCat("Vasya",350, 3);

        for (Animal animal: arrayOfAnimals){
            animal.run(150);
            animal.jump(2.5);
            animal.swim(5);
        }
    }
}
