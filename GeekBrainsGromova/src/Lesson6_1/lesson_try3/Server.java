package Lesson6_1.lesson_try3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static DataInputStream in;
    private static DataOutputStream out;
    private static Scanner scanner;
    private static PrintWriter writer;

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8112);
            System.out.println("Сервер запущен!");

            socket = server.accept();
            System.out.println("Клиент подключился!");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
            writer = new PrintWriter(socket.getOutputStream(), true);
            StringBuffer msg = new StringBuffer();
            Scanner input = new Scanner(System.in);

            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName());


            new Thread(new Runnable() {
                @Override
                public void run() {

                    System.out.println("Введите сообщение для клиента:");
                    while (input.hasNextLine()) {
                        String str = input.nextLine();
                        if (str.equals("/end")) {
                            break;
                        }
                        msg.append(str + " ");
                    }

                    writer.flush();
                    writer.println("Server answer: " + msg);

                    try {
                        out.writeUTF("Server answer: " + msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }}).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.isEmpty()) {
                                break;
                            }
                            if (str.equals("/clientClosed")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }
                            System.out.println("Сообщение от клиента серверу (чтение из потока): " + str);
                        }

                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                }}).start();




//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    //readMessage();
//                    sendConsolMessageToClient();
//                }
//            }).start();
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    readMessage();
//                    //sendConsolMessageToClient();
//                }
//            }).start();



        } catch (IOException e) {
            e.printStackTrace();
        }


        finally {
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





    static void sendConsolMessageToClient(){
        Scanner input = new Scanner(System.in);
        String str;
        StringBuffer msg = new StringBuffer();
        System.out.println("Введите сообщение для клиента:");
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
        return;
    }

    static void readMessage() {
        try {
            String str = in.readUTF();
            if (str.isEmpty()){
                return;
            }
            if (str.equals("/clientClosed")) {
                out.writeUTF("/serverClosed");
                return;
            }
            System.out.println("Сообщение от клиента серверу (чтение из потока): " + str);
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
            out.writeUTF("Server clone answer: " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
