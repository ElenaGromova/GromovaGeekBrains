package lesson5;

import lesson4HomeWork.Cat;

public class TestCatEat {

    public static void main(String[] arg) {
        int n = 3;
        int min;

        CatEat[] catEatArray = new CatEat[5];
        catEatArray[0] = new CatEat("Barsik", 2);
        catEatArray[1] = new CatEat("Mudwesia", 3);
        catEatArray[2] = new CatEat("Tasia", 7);
        catEatArray[3] = new CatEat("Lusia", 5);
        catEatArray[4] = new CatEat("Kisa", 1);

        Miska miska = new Miska(15, 10);

        for (int j = 1; j <= n; j++){
            System.out.println("-------------");
            System.out.println("В миске " + miska.realEat + " Котики кушайте снова в порядке очереди");
            for (int i = 0; i < 5; i++) {
                catEatArray[i].full = miska.kotikPoel(catEatArray[i].eat);
                miska.getRealEat(catEatArray[i].eat);
                catEatArray[i].printCatFull();
            }
            if (miska.realEat <= 1) {
                System.out.println("Миска пустая(");
                miska.addFood(2); //наполнили миску максимально
            }
        }

    }
}
