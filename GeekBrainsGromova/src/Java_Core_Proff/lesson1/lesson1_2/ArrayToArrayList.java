package Java_Core_Proff.lesson1.lesson1_2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayToArrayList<T>{
    private ArrayList<T> arrayList;

    ArrayToArrayList(T[] array) {
        //Collections.addAll(this.arrayList, array);
        this.arrayList = new ArrayList<>(Arrays.asList(array));
    }

    void info(){
        for(int i = 0; i< arrayList.size(); i++) {
            System.out.print(arrayList.get(i) + " ");
        }
        System.out.println();
        //System.out.print(arrayList.toString());
    }

    public static void main(String[] args) {
        String[] array1 = { "ABC", "DEF", "GHI", "JKL"};
        Integer[] array2 = {1, 2, 3, 4};
        Double[] array3 = {1.0, 2.0, 3.0, 4.0};
        ArrayToArrayList arr1 = new ArrayToArrayList(array1);
        ArrayToArrayList arr2 = new ArrayToArrayList(array2);
        ArrayToArrayList arr3 = new ArrayToArrayList(array3);

        arr1.info();
        arr2.info();
        arr3.info();
    }

}
