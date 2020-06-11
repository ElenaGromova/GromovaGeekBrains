package Java_Core_Proff.lesson3.lesson3_5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class СonverselyRead {
    public static void main(String[] args)throws IOException {
        RandomAccessFile raf = new RandomAccessFile("files/file1.txt", "r");
        for(int i = (int) (raf.length() - 1); i>=0; i--){
            raf.seek(i);
            System.out.print((char)raf.read());
        }
    }
}
