package Java_Core_Lesson7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServ {
    private Vector<ClientHandler> clients;

    public MainServ() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                //subscribe(new ClientHandler(this, socket));
                new ClientHandler(this, socket);
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
            AuthService.disconnect();
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true ;
            }
        }
        return false ;
    }

    public ClientHandler getByNick(String nick){
        for (ClientHandler o: clients) {
            if (o.getNick().equals(nick)) {
                return o;
            }
        }
        return null;
    }

    public void broadcastMsg(String msg, String nick) {
        if (msg.contains("/w")){
            String[] partsOfMsg = msg.split(" ");


// проверка ников с пробелом (не решит проблему отличия "nick3" и "nick3 nick3")
            for (ClientHandler o: clients){
                if (msg.startsWith("/w" + " " + o.getNick())){   //// или  if (msg.contains(o.getNick()) & (msg.indexOf(o.getNick())==3)
                    String personalMsg = msg.replace(("/w" + " " + o.getNick()), "");
                    o.sendMsg(nick + ": " + personalMsg);
                    getByNick(nick).sendMsg(nick + ": " + personalMsg);
                    return;
                }
            }
//вариация для ника = одно слово
//            for (ClientHandler o: clients){
//                if (o.getNick().equals(partsOfMsg[1])) {
//                    String[] str = msg.split(partsOfMsg[1] + " ");
//                    String personalMsg = str[1];
//                    o.sendMsg(nick + ": " + personalMsg);
//                    getByNick(nick).sendMsg(nick + ": " + personalMsg);
//                    return;
//                }
//            }
            getByNick(nick).sendMsg("Сообщение не отправлено: Пользователя с таким ником нет в сети!");
        }
        else {
            for (ClientHandler o : clients) {
                o.sendMsg(nick + ": " + msg);
            }
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

//вывод в консоль данных клиентов
//    public void info(){
//        for (ClientHandler o : clients) {
//            System.out.println("Client with nick " + o.nick + " login " + o.login + " password " + o.password);
//        }
//    }
}
