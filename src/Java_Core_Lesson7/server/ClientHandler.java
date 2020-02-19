package Java_Core_Lesson7.server;

import com.sun.org.apache.xerces.internal.xni.XMLString;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    DataInputStream in;
    DataOutputStream out;
    MainServ serv;
    String nick;
    String login;
    String password;

    public ClientHandler(MainServ serv, Socket socket){
        try {
            this.socket = socket;
            this.serv = serv;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
//            this.login = login;
//            this.password = password;
            this.nick = nick;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.startsWith("/auth")) {
                                String[] tokens = msg.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
//                                login = tokens[1];
//                                password = tokens[2];
                                if (newNick == null) {
                                    sendMsg("Неверный логин/пароль");
                                } else {
                                    if ((serv.isNickBusy(newNick))) {
                                        sendMsg("Такая УЗ уже существует!");
                                    } else {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        serv.subscribe(ClientHandler.this);
                                        break;
                                    }
                                }
                            }
                        }


                        while (true) {
                            String msg = in.readUTF();
                            if (msg.equals("/end")) {
                                out.writeUTF("/serverClosed");
                                break;
                            }

                            //можно было бы, чтобы удалить отключенных клиентов перебрать их список тут
                            //list client = server.getClientList();
                            //for (int i=0; i<client.size(); i++){
                            //    if (client.get(i).getSocket.IsClosed()){
                            //        client.remove(i);
                            //    }
                            //}

                            //вывод в консоль данных клиентов
                            // serv.info();
                            serv.broadcastMsg(msg, nick);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //удаление клиента, если он отключился
                        serv.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }

}
