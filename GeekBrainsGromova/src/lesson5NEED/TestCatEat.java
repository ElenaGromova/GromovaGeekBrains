package lesson5NEED;

import lesson4HomeWork.Cat;

public class TestCatEat {

    public static void main(String[] arg) {
        CatEat[] catEatArray = new CatEat[5];
        Miska miska = new Miska(25, 25);

        //создаем котов
        for (int i = 0; i < catEatArray.length; i++) {
            catEatArray[i] = new CatEat("Cat " + i, 5 + i);
        }

        //кормим котов
        for (CatEat cat : catEatArray) {
            cat.eat(miska);
        }

        //выводим сытость котов
        for (CatEat cat : catEatArray) {
            if (cat.isCatHungry())
                System.out.println(cat.getName() + " голоден");
            else System.out.println(cat.getName() + " сыт");
        }

    }
}
