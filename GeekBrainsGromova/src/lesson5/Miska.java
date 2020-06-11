package lesson5;

import lesson4HomeWork.Cat;

public class Miska{
    int maxEat; //макс объем кормушки=еды в ней
    int realEat; //сколько еды в кормушке

    Miska(int maxEat, int realEat) {
        this.maxEat = maxEat; //макс объем кормушки=еды в ней
        this.realEat = realEat; //сколько еды в кормушке
    }

    //метод наполнения кормушки
    public int addFood(int addFood) {
        while (maxEat>(realEat + addFood)) {realEat += addFood;}
        return realEat;
    }

    //метод получения информации о количестве еды
    public int getRealEat(int eat){
        realEat = (realEat>eat) ? (realEat - eat) : realEat;
        return (realEat);
    }

    public boolean kotikPoel(int eat){
        return realEat>eat;
    }

}
