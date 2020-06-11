package Java_Core_Lesson2;
import static Java_Core_Lesson2.MainExceptions.size;
import static java.lang.Integer.parseInt;

public class MyArrayDataException extends Exception{

    public MyArrayDataException(String msg) {
        super(msg);
    }
}

class ArrayData{

    static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

     static int SumArrayData(String[][] arr) throws MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!checkString(arr[i][j]))
                    throw new MyArrayDataException("Нельзя преобразовать в целое число данные из ячейки: i= " + i + " j= " + j);
                sum = sum + parseInt(arr[i][j]);
            }
        }
        System.out.println("Сумма элементов массива = " + sum);
        return sum;
    }
}