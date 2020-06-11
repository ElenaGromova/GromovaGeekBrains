package Java_Core_Proff.lesson6.CheckArray;

public class CheckArray {
    public boolean checkArray(int[] arr){
        int count1 = 0;
        int count4 = 0;
        for (int i = 0; i < arr.length; i++){
            if ((arr[i] != 4) & (arr[i] != 1)) {
                return false;
            }
            if (arr[i] == 4) {count4++;}
            if (arr[i] == 1) {count1++;}
        }
        return ((count1 != 0) & (count4 != 0));
    }
}
