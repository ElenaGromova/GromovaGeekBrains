package Lesson6_1.lesson_try3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Scanner scanner;
    private static PrintWriter writer;

    public static void main(String[] args) {
    Socket socket;

    final String IP_ADRESS = "localhost";
    final int PORT = 8112;

    try {
        socket = new Socket(IP_ADRESS, PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);

        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName());
//        Scanner input = new Scanner(System.in);
//        StringBuffer msg = new StringBuffer();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                System.out.println("Введите сообщение для server:");
//                while (input.hasNextLine()) {
//                    String str = input.nextLine();
//                    if (str.equals("/end")) {
//                        break;
//                    }
//                    msg.append(str + " ");
//                }
//
//                writer.flush();
//                writer.println("Client answer: " + msg);
//
//                try {
//                    out.writeUTF("Client answer: " + msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }}).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    while (true) {
//                        String str = in.readUTF();
//                        if (str.isEmpty()) {
//                            break;
//                        }
//                        if (str.equals("/serverClosed")) {
//                            out.writeUTF("/clientClosed");
//                            break;
//                        }
//                        System.out.println("Сообщение от сервера клиенту (чтение из потока): " + str);
//                    }
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }}).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //sendConsolMessageToServer();
                readMessage();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendConsolMessageToServer();
                //readMessage();
            }
        }).start();


        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private static void sendConsolMessageToServer(){
        Scanner input = new Scanner(System.in);
        String str;
        StringBuffer msg = new StringBuffer();
        System.out.println("Введите сообщение для server:");
        while (input.hasNextLine()) {
            str = input.nextLine();
            if (str.equals("/end")) {
                break;
            }
            msg.append(str + " ");
        }
        System.out.println(msg);

        sendMessageByWriter(msg.toString());
        sendMessageByStream(msg.toString());
        //return;
    }

    static void readMessage() {
            try {
                String str = in.readUTF();
                if (str.isEmpty()) {
                    return;
                }
            if (str.equals("/serverClosed")) {
                out.writeUTF("/clientClosed");
                return;
            }
            System.out.println("Сообщение от сервера клиенту (чтение из потока): " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessageByWriter(String msg) {
        writer.flush();
        writer.println(msg);
    }

    private static void sendMessageByStream(String msg){
        try {
            out.writeUTF("Server answer: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
