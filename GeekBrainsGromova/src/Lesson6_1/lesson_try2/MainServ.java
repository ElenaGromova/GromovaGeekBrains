package Lesson6_1.lesson_try2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServ {
    private ClientHandler client;

    public MainServ() {
        //client = new ClientHandler();
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8150);
            System.out.println("Сервер запущен!");

            while (true) {
                System.out.println("ждем!");
                socket = server.accept();
                System.out.println("Клиент подключился!");
                client = new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
            client.sendMsg(msg);
    }

    public static void main(String[] args) {
    new MainServ();
    }
}