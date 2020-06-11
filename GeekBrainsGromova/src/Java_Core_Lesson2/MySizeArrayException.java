package Java_Core_Lesson2;

import static Java_Core_Lesson2.MainExceptions.size;

public class MySizeArrayException extends Exception {

    public MySizeArrayException(String msg) {
        super(msg);
    }
}

    class ArraySize{
        public static void checkArraySize(String[][] arr) throws MySizeArrayException {
            boolean sizeCheck = false;
            for (int i = 0; i < arr.length; i++){
                if (arr[i].length != size) {
                    sizeCheck = true;
                    break;
                }
            }
            if ((arr.length != size) || sizeCheck) {
                throw new MySizeArrayException("Длина массива отличается от " + size);}
        }
    }



