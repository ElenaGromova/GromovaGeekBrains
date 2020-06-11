package Java_Core_Proff.lesson1.lesson1_3;

import java.util.ArrayList;

public class TestFruitBox {
    public static void main(String[] args) {
        ArrayList<Apple> apples = new ArrayList<>();
        apples.add(new Apple(0.3));
        apples.add(new Apple(0.2));

        ArrayList<Orange> oranges = new ArrayList<>();
        oranges.add(new Orange(0.3));
        oranges.add(new Orange(0.2));

        ArrayList<Apple> apples1 = new ArrayList<>();
        apples1.add(new Apple(0.5));

        Box boxApple1 = new Box(apples, apples.get(0).getName(), 0.9);
        Box boxApple2 = new Box(apples1, apples1.get(0).getName(), 1.7);
        Box boxOrange = new Box(oranges, oranges.get(0).getName(), 0.7);

        boxApple1.info();
        boxApple2.info();
        boxOrange.info();

        boxOrange.putSomeFruit(new Orange(0.2));
        boxOrange.putSomeFruit(new Apple(0.3));

        System.out.println( boxOrange.compare(boxApple1) ? "Коробки равны по весу!" : "Коробки разные по весу!");

        boxApple1.putIntoAnotherBoxFruit(boxOrange);
        boxApple1.putIntoAnotherBoxFruit(boxApple2);

        boxApple1.info();
        boxApple2.info();
        boxOrange.info();
    }
}
