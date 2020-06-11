package Lesson6_1.lesson_try1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?

    final String IP_ADRESS = "localhost";
    final int PORT = 8172;

    public static void main(String[] args) {
        try {
            try {
                System.out.println("client");
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                socket = new Socket("localhost", 8172); // этой строкой мы запрашиваем
                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("Вы что-то хотели сказать? Введите это здесь:");
                // если соединение произошло и потоки успешно созданы - мы можем
                //  работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение
                String word = reader.readLine(); // ждём пока клиент что-нибудь
                // не напишет в консоль
                out.write(word + "\n"); // отправляем сообщение на сервер
                out.flush();
                String serverWord = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverWord); // получив - выводим на экран
            }
            finally { // в любом случае необходимо закрыть сокет и потоки
//                System.out.println("Клиент был закрыт...");
//                socket.close();
//                in.close();
//                out.close();
            }
            } catch (IOException e) {
                System.err.println(e);
            }
        }


//    public void run() {
//        try {
//            socket = new Socket(IP_ADRESS, 8192);
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (true) {
//                            String srt = in.readUTF();
//                            if (srt.equals("/serverClosed")) {
//                                break;
//                            }
//                            System.out.println(srt + "\n");
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            socket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }).start();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    Socket socket;
//    DataInputStream in;
//    DataOutputStream out;
//    Server serv;
//
//    public Client(Server serv, Socket socket) {
//        try {
//            this.socket = socket;
//            this.serv = serv;
//            this.in = new DataInputStream(socket.getInputStream());
//            this.out = new DataOutputStream(socket.getOutputStream());
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while (true) {
//                            String msg = in.readUTF();
//                            if (msg.equals("/end")) {
//                                out.writeUTF("/serverClosed");
//                                break;
//                            }
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        try {
//                            in.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            out.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            socket.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }








    public void readServerMsgFromStream() {
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

    public void readServerMsgFromConsol(){
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            String msg = scanner.nextLine();
            while (scanner.hasNextLine()) {
                msg += scanner.nextLine();
            }
            if (msg.equals("/end")) {
                break;
            }
            System.out.println("From Client from consol " + msg);
            out.flush();
            out.println("echo to server " + msg);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToServer(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сообщение клиента серверу:" +msg);
    }

//    public static void main(String[] args) {
//        //new Client();
//    }


}
