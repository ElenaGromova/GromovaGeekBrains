package Java_Core_Lesson5;

public class MainArraysMerge {

    public static void main(String[] args) {
        ArraysMerge arr = new ArraysMerge();
        arr.initArray();
        arr.changeArrayValuesOneThread();
        arr.changeArrayValuesTwoThread();
        arr.changeArrayValuesSameThread(3);
    }
}
