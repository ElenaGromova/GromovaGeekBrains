package Java_Core_Proff.lesson6.ChooseFromArray;

public class ArrayForChoose {
    private int n = 4;
    public int[] chooseFromArray(int[] arr) {
        int[] arrayRes;
        int j = -1;
        for (int i = (arr.length - 1); i >= 0; i--) {
            if (arr[i] == n) {
                j = i;
                break;
            }
        }

        if (j != -1) {
            arrayRes = new int[arr.length - j - 1];
            int size = 0;
            for (int i = (j + 1); i < arr.length; i++) {
                arrayRes[size] = arr[i];
                size++;
            }
        } else {
            throw new RuntimeException();
        }
        return arrayRes;
    }
}

