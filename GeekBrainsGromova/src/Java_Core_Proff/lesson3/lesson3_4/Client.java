package Java_Core_Proff.lesson3.lesson3_4;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cat cat = new Cat("Barsik", "White");
        Socket socket = new Socket("localhost", 8155);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(cat);
        oos.close();
        cat.info();
    }
}
