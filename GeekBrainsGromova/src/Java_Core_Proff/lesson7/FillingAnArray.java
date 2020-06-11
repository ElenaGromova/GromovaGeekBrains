package Java_Core_Proff.lesson7;

public class FillingAnArray {
    public static int fillingSquare(int i1, int i2, int j1, int j2, int element, int[][] array){
        for (int j = j1; j <= j2; j++){
            array[i1][j] = element;
            element++;
        }
        for (int i = (i1+1); i <= i2; i++){
            array[i][j2] = element;
            element++;
        }
        for (int j = (j2 - 1); j >= j1; j--){
            array[i2][j] = element;
            element++;
        }
        for (int i = (i2 - 1); i > i1; i--){
            array[i][j1] = element;
            element++;
        }
        return element;
    }

    public static void arrayPrint(int[][] array, int width, int length){
        for (int i = 0; i < width; i++){
            for (int j = 0; j < length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void main(String[] args){
        int length = 7;
        int width = 5;
        int middle = Math.round((width-1)/2);
        int element = 10;
        int[][] array = new int[width][length];
        int iStart = 0;
        int jStart = 0;
        int iEnd = width - 1;
        int jEnd = length - 1;

        while (iStart <= middle){
            if ((iStart == middle) & (width%2 != 0)) {
                for (int j = jStart; j <= jEnd; j++) {
                    array[iStart][j] = element;
                    element++;
                }
            }
            else {
                element = fillingSquare(iStart, iEnd, jStart, jEnd, element, array);
            }
            iStart++;
            iEnd--;
            jStart++;
            jEnd--;
        }

        arrayPrint(array, width, length);
    }
}
