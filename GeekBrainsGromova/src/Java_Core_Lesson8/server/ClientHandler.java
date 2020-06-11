package Java_Core_Lesson8.server;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static Java_Core_Lesson8.server.AuthService.*;

public class ClientHandler {
    @FXML
    ListView<String> clientsBlackList;

    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    private int id;

    HashSet<String> blackListNicks;
    HashSet<Integer> blackListIds;

    public String getNick() {
        return nick;
    }
    public int getId() {return id;}

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackListNicks = new HashSet<String>();
            new Thread(() -> {
                try {
                    while (true) {
                        socket.setSoTimeout(120000);
                        String msg = in.readUTF();
                        System.out.println("msg " + msg);
                        if (msg.startsWith("/auth")) {
                            String[] tokens = msg.split(" ");
                            String newNick = getNickByLoginAndPass(tokens[1], tokens[2]);
                            Integer newId = getIdByLoginAndPass(tokens[1], tokens[2]);
//                                login = tokens[1];  /blacklist nick2   login pass
//                                password = tokens[2];
                            if (newNick == null) {
                                sendMsg("Неверный логин/пароль");
                            } else {
                                if ((server.isNickBusy(newNick))) {
                                    sendMsg("Такая УЗ уже существует!");
                                } else {
                                    sendMsg("/authok " + newId);
                                    nick = newNick;
                                    id = newId;
                                    blackListNicks = blackListInit(id);
//                                    Iterator<Integer> i = blackListIds.iterator();
//                                    while (i.hasNext()){
//                                        blackListNicks.add(getNickById(i.next()));
//                                    }
                                    //createBlackList(blackListNicks);
                                    server.subscribe(ClientHandler.this);
                                    broadcastBlackClientsList();
                                    break;
                                }
                            }
                        }
                        if (msg.startsWith("/reg")){
                            String[] tokens = msg.split(" ");
                            this.nick = tokens[3];
                            String s = checkRegistration(msg);
                            if (s.equals("/regok")){
                                System.out.println("создаем клиента");
                                server.subscribe(ClientHandler.this);
                                System.out.println("ник " + ClientHandler.this.nick);
                            }
                            sendMsg(s);
                            break;
                        }
                    }
                    while (true) {
                        socket.setSoTimeout(120000);
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w ")) { // /w nick3 lsdfhldf sdkfjhsdf wkerhwr
                                String[] tokens = str.split(" ", 3);
                                String m = str.substring(tokens[1].length() + 4);
                                if (!checkBlackList(tokens[2])) {
                                    server.sendPersonalMsg(this, tokens[1], tokens[2]);
                                } else {
                                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список и не можете отправить ему ЛС");
                                }
                            }
                            if (str.startsWith("/blacklist ")) { // /blacklist nick3
                                String[] tokens = str.split(" ");
                                if (!blackListNicks.contains(tokens[1])){
                                    blackListNicks.add(tokens[1]);
                                    addUserToBlackList(getIdByNick(nick), getIdByNick(tokens[1]));
                                    broadcastBlackClientsList();
                                    //clientsBlackList.getItems().add(tokens[1]);
                                    System.out.println("sendMsg(\"Вы добавили пользователя \" + tokens[1] + \" в черный список\");");
                                    sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                    }
                                    else {
                                    System.out.println("sendMsg(\"Пользователь \" + tokens[1] + \" уже у вас в черном списке\");");
                                        sendMsg("Пользователь " + tokens[1] + " уже у вас в черном списке");
                                }
                            }
                            if (str.startsWith("/outblacklist ")) { // /outblacklist nick3
                                String[] tokens = str.split(" ");
                                broadcastBlackClientsList();
                                System.out.println("tokens[1] " + tokens[1]);
                                if (!blackListNicks.contains(tokens[1])) {
                                    blackListNicks.remove(tokens[1]);
                                    //clientsBlackList.getItems().remove(tokens[1]);
                                    deleteUserToBlackList(getIdByNick(nick), getIdByNick(tokens[1]));
                                    sendMsg("Вы удалили пользователя " + tokens[1] + " из черного списка");
                                }
                            }
                        } else {
                            server.broadcastMsg(this, nick + ": " + str);
                        }
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
                    server.unsubscribe(this);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
        return blackListNicks.contains(nick);
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastBlackClientsList() {
        //clientsBlackList.getItems().clear();
        //clientsBlackList.getItems().add("Черный список:");
        StringBuilder sb = new StringBuilder();
        System.out.println(sb);
        Iterator<String> i = blackListNicks.iterator();
        while (i.hasNext()){
            sb.append(i.next() + " ");
            //clientsBlackList.getItems().add(i.next());
        }
        String out = sb.toString();
        System.out.println("черный список " + out);
        //sendMsg(out);
    }

}
