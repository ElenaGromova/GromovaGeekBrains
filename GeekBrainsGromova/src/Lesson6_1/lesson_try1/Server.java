package Lesson6_1.lesson_try1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    //private static Socket socket;
    private static ServerSocket server;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args){
        try {
            try  {
                server = new ServerSocket(8172);
                System.out.println("Сервер запущен!");
                Socket socket = server.accept();
                try {
                    Scanner scanner = new Scanner(socket.getInputStream());
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    String word = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    System.out.println(word);
                    // не долго думая отвечает клиенту
                    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                    out.flush(); // выталкиваем все из буфера

                } finally { // в любом случае сокет будет закрыт
                    System.out.println("сокет будет закрыт");
                    socket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //public Server(){
        //Client client;
        //ServerSocket server = null;
        //Socket socket = null;
//        try {
//            server = new ServerSocket(8193);
//            System.out.println("Сервер запущен!");
//
//            while (true) {
//                socket = server.accept();
//                System.out.println("Клиент подключился!");
//                //client = new Client(this, socket);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                socket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                server.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }



    public void readClientMsgFromStream() {
        try {
            while (true) {
                String msg = in.readUTF();
                if (msg.equals("/end")) {
                    out.writeUTF("/serverClosed");
                    break;
                }
                System.out.println("прочел из потока " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void readClientMsgFromConsol(){
//        try {
//            Scanner scanner = new Scanner(socket.getInputStream());
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//            while (true) {
//                String msg = scanner.nextLine();
//                while (scanner.hasNextLine()) {
//                    msg += scanner.nextLine();
//                }
//                if (msg.equals("/end")) {
//                    break;
//                }
//                System.out.println("From Server to console " + msg);
//                out.flush();
//                out.println("echo to client " + msg);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void sendMsgToClient(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сообщение от сервера клиенту: " +msg);
    }


}
