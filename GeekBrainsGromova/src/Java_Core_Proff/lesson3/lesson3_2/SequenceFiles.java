package Java_Core_Proff.lesson3.lesson3_2;

import Java_Core_Proff.lesson3.lesson3_1.FileRead;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class SequenceFiles {

    public static void FileRead(String file) throws IOException{
        FileInputStream in = new FileInputStream(file);
        byte[] arr = new byte[100];
        int x;
        while ((x = in.read(arr)) != -1){
            System.out.println(new String(arr, 0, x, "UTF-8"));
        }
    }

    public static void main(String[] args)throws IOException{
//        File file = new File("files/1.txt");
////        file.createNewFile();
////        File file1 = new File("files/2.txt");
////        file1.createNewFile();
////        File file2 = new File("files/3.txt");
////        file2.createNewFile();
////        File file3 = new File("files/4.txt");
////        file3.createNewFile();
////        File file4 = new File("files/5.txt");
////        file4.createNewFile();
        ArrayList<InputStream> arr = new ArrayList<>();
        arr.add(new FileInputStream("files/1.txt"));
        arr.add(new FileInputStream("files/2.txt"));
        arr.add(new FileInputStream("files/3.txt"));
        arr.add(new FileInputStream("files/4.txt"));
        arr.add(new FileInputStream("files/5.txt"));

        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(arr));
        FileOutputStream out = new FileOutputStream("files/file1.txt");

        int x;
        while ((x = in.read()) != -1) {
            out.write(x);
        }

        in.close();
        FileRead("files/file1.txt");

    }
}
