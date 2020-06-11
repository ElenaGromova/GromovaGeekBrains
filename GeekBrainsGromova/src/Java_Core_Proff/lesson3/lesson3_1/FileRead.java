package Java_Core_Proff.lesson3.lesson3_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) throws IOException{
//        File file = new File("files/file1.txt");
//        file.createNewFile();
        try (FileInputStream in = new FileInputStream("files/file1.txt")) {
            byte[] arr = new byte[50];
            int x;
            while ((x = in.read(arr)) != -1){
                System.out.println(new String(arr, 0, x, "UTF-8"));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
