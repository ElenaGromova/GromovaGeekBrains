package Java_Core_Lesson5;

import java.util.HashMap;
import java.util.Map;

class ArraysMerge {
    static final int SIZE = 10000000;
    static final int HALF = SIZE/2;
    private float[] arr;
    private int arrValue = 1;
    private Map<Integer, float[]> arrays = new HashMap<>();

    ArraysMerge(){
        this.arr = new float[SIZE];
    }

    void initArray(){
        long initTime = System.currentTimeMillis();
        for (int i=0; i<SIZE; i++){
            arr[i] = arrValue;
            //arr[i]=i+1;
        }
        System.out.println("Исходный массив заполнился единицами за (миллисекунд): " + (System.currentTimeMillis() - initTime));
    }

    private float[] changeArrayValues(float[] arr){
        for (int i=0; i<arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2));
            //arr[i] = arr[i] * 2;
        }
        return arr;
    }

    //один поток
    void changeArrayValuesOneThread(){
        long initTime = System.currentTimeMillis();
        changeArrayValues(arr);
        System.out.println("Значения элементов исходного массива изменены на расчетные в один поток за (миллисекунд): " + (System.currentTimeMillis() - initTime));
    }

    //два потока
    void changeArrayValuesTwoThread(){
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[SIZE-HALF];

        long initTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, SIZE-HALF);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeArrayValues(arr1);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeArrayValues(arr2);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, SIZE-HALF);

        System.out.println("Значения элементов исходного массива изменены на расчетные в два потока за (миллисекунд): " + (System.currentTimeMillis() - initTime));
    }

    //разбиваем исходный массив
    private void separationArray(int n){
        int sizeArr = SIZE/n;
        int count = 0;
        int i = 0;

        while ((SIZE - count)>sizeArr){
            float[] arrPart = new float[sizeArr];
            System.arraycopy(arr, count, arrPart, 0, sizeArr);
            arrays.put(i, arrPart);
            count += sizeArr;
            i++;
        }

        float[] arrPart = new float[SIZE - count];
        System.arraycopy(arr, count, arrPart, 0, (SIZE - count));
        arrays.put(i, arrPart);
    }

    //собираем исходный массив
    private void collectArray(){
        int count = 0;
        float[] result = new float[SIZE];

        for (Map.Entry<Integer, float[]> entry : arrays.entrySet()){
            System.arraycopy(entry.getValue(), 0, result, count, entry.getValue().length);
            count += entry.getValue().length;
        }

        this.arr = result;
    }

    //несколько потоков
    void changeArrayValuesSameThread(int n) {
        String threadName;
        long initTime = System.currentTimeMillis();
        separationArray(n);

        for (int i = 0; i<n; i++){
            threadName = "Thread" + i;
            new MyThread(threadName, arrays.get(i));
        }

        collectArray();

        System.out.println("Значения элементов исходного массива изменены на расчетные в " + n + " потока(-ов) за (миллисекунд): " + (System.currentTimeMillis() - initTime));

    }

    class MyThread implements Runnable{
        float[] array;
        String name;

        MyThread(String name, float[] array){
            this.name = name;
            this.array = array;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            changeArrayValues(array);
        }
    }

//    private void arrayElements(float[] array){
//        for (int i = 0; i<array.length; i++) System.out.print(array[i] + "   ");
//        System.out.println();
//    }

}
