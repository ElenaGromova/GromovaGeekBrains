package Java_Core_Lesson2;

import static Java_Core_Lesson2.ArrayData.SumArrayData;
import static Java_Core_Lesson2.ArraySize.checkArraySize;

public class MainExceptions {
    static int size = 4;
    static String[][] array1 = {{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"},{"1","2","3","4"}};
    static String[][] array2 = {{"1","2","3","4","5jkl"},{"1","2","3","4","5"},{"1","2","3","4","5"},{"1","2","3","4","5"}};
    static String[][] array3 = {{"1","2","3","4ghj"},{"1","2","3","4"},{"1","2","3","4"},{"1fds","2","3","4"}};

    public static void main(String[] args) throws MyArrayDataException, MySizeArrayException {
        checkArray(array1);;
        checkArray(array2);
        checkArray(array3);
    }

    static void checkArray(String[][] arr)throws MyArrayDataException, MySizeArrayException{
        try {
            checkArraySize(arr);
            int sum = SumArrayData(arr);
        } catch(MyArrayDataException | MySizeArrayException e){
            e.printStackTrace();
        }
    }

}
