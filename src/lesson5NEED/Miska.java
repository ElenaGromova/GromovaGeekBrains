package lesson5NEED;

public class Miska{
    int foodAmount; //сколько еды в кормушке
    int plateVolume; // макс объем кормушки=еды в ней

    public  Miska(int userPlateVolume, int userFoodAmount) {
        this.plateVolume = userPlateVolume; //макс объем кормушки=еды в ней

        if (userPlateVolume<=userFoodAmount){
            this.foodAmount = userFoodAmount;
        }
        else {
            System.out.println("Миска слишком маленькая");
        }
    }

    public  boolean checkFoodAmount(int foodDelta){
        return foodDelta <= foodAmount;
    }
    public void decreaseFoodAmojnt(int foodDelta){
        if (foodDelta > foodAmount){
            System.out.println("В миске нет столько еды!");
        }
        else {
            foodAmount -=foodDelta;
        }
    }

    public void info(){System.out.println("В миске осталось " + foodAmount);}

    public void putSomeFood(){
        this.foodAmount += (this.plateVolume - this.foodAmount); //заполняем миску
        System.out.println("Миска наполнена");
        info();
    }

}
