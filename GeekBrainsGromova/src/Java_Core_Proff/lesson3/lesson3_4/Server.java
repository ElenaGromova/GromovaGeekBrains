package Java_Core_Proff.lesson3.lesson3_4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = null;
        ServerSocket server = new ServerSocket(8155);
        while (socket==null) socket = server.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Cat cat = (Cat) ois.readObject();
        ois.close();
        cat.info();
    }
}
