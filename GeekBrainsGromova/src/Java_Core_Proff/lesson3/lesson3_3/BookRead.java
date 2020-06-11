package Java_Core_Proff.lesson3.lesson3_3;

import java.io.*;

public class BookRead {
    static int Size = 1800;
    static int n = 3;

    public static void main(String[] args)throws IOException{
        FileInputStream in = new FileInputStream("files/book.txt");
        byte[] arr = new byte[Size];
        int x;
        int i = 1;
        while ((x = in.read(arr)) != -1){
            long t = System.currentTimeMillis();
            System.out.println("Страница " + i);
            System.out.println(new String(arr, 0, x, "UTF-8"));
            i++;
            System.out.println(System.currentTimeMillis() - t);
            if ((System.currentTimeMillis() - t) > 5000) {
                System.out.println("Чтение страницы заняло более 5 секунд - " + (System.currentTimeMillis() - t));
            }
        }

    }
}
