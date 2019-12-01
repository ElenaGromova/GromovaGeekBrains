package lesson2;

public class lesson2 {

    private static int length = 5;
    private static int elem = 1;

    public static void main(String[] args){
        int[] array = new int[10];
        int[] array2 = new int[8];
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[][] array4 = new int[length][length];
        int[] checkBalance1 = {1, 1, 1, 2, 1};
        int[] checkBalance2 = {2, 1, 1, 2, 1};
        int[] checkBalance3 = {10, 10};
        int[] array5 = {1, 2, 3 , 4, 5};

        generateArray(array);
        printArray(array, "Ваш исходный массив: ");
        changeArray(array);
        printArray(array, "Ваш преобразованный массив: ");

        generateArr(array2, 3);
        printArray(array2, "Ваш кратный массив: ");

        changeArrayElments(array3, 6);
        printArray(array3, "Ваш массив c некоторыми удвоенными элементами: ");
        System.out.println("Минимальный элемент этого массива: " + minArrElement(array3));
        System.out.println("Максимальный элемент этого массива: " + maxArrElement(array3));

        doubleArray(array4);
        System.out.println("Двумерный массив с диагональю из единиц: ");
        for ( int i=0; i<length; i++)
            printArray(array4[i], "");

        System.out.println("Проверим есть ли в массивах место, в котором сумма левой и правой части массива равны: ");
        printArray(checkBalance1, "Массив1: ");
        System.out.println(checkSumArray(checkBalance1) ? "true" : "false");
        printArray(checkBalance2, "Массив2: ");
        System.out.println(checkSumArray(checkBalance2) ? "true" : "false");
        printArray(checkBalance3, "Массив3: ");
        System.out.println(checkSumArray(checkBalance3) ? "true" : "false");

        printArray(array5, "Ваш исходный массив: ");
        moveArrElements(array5, 2);
        printArray(array5, "Ваш сдвинутый массив: ");

        printArray(array5, "Ваш исходный массив: ");
        moveArrElements(array5, -4);
        printArray(array5, "Ваш сдвинутый массив: ");


    }

    static void printArray(int[] arr, String text){
        System.out.print(text);
        for (int element : arr)
            System.out.print(element + " ");
        System.out.println();
    }

    static int[] generateArray(int[] arr){
        for (int i=0; i<arr.length; i++)
            arr[i] = (int) (Math.random() * 2);
        return arr;
    }

    static int[] changeArray(int[] arr){
        for (int i=0; i<arr.length; i++)
           switch (arr[i]){
               case 0: arr[i] = 1; break;
               case 1: arr[i] = 0; break;
           }
        return arr;
    }

    static int[] generateArr(int[] arr, int n){
        for (int i=0; i<arr.length; i++)
            arr[i] = i*n;
        return arr;
    }

    static int[] changeArrayElments(int[] arr, int n){
        for (int i=0; i<arr.length; i++) {
            if (arr[i]<n)
                arr[i] *= 2;
        }
        return arr;
    }

    static int[][] doubleArray(int[][] arr){
        for (int i=0; i<length; i++)
            for (int j=0; j<length; j++)
                if (i==j)
                    arr[i][j] = elem;
                else arr[i][j] = (int) (Math.random() * 10);
         return arr;
    }

    static int minArrElement(int[] arr){
        int min = arr[0];
        for (int element : arr)
            if (element<min)
                min = element;
        return min;
    }

    static int maxArrElement(int[] arr){
        int max = arr[0];
        for (int element : arr)
            if (element>max)
                max = element;
        return max;
    }

    static int sumArray(int[] arr, int start, int end){
        int sum = 0;
        for (int i = start; i<= end; i++)
            sum +=arr[i];
        return sum;
    }

    static boolean checkSumArray(int[] arr){
        boolean res = false;
        for (int i=0; i<arr.length; i++) {
            if (sumArray(arr, 0, i) == sumArray(arr, (i + 1), (arr.length - 1))) {
                res = true;
                break;
            }
        }
        return res;

    }

    static int[] moveArrElements(int[] arr, int n){
        int buf, j;
        int score = 1;
        if (n==0) {
            return arr;
        } else
            while (score <= Math.abs(n)) {
                if (n>0) {
                    for (int i = 0; i < (arr.length - 1); i++) {
                            buf = arr[i];
                            j = arr.length - 1;
                            arr[i] = arr[j];
                            arr[j] = buf;
                            j--;
                        }
                    }
                    else {
                    for (int i=(arr.length - 1); i>0; i--){
                        buf = arr[i];
                        j=i-1;
                        arr[i] = arr[j];
                        arr[j] = buf;
                    }
                }
                score++;
            }
    return arr;
    }

}
