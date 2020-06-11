package Java_Core_Proff.lesson6.lesson2_2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.*;

public class Server {
    private Vector<ClientHandler> clients;
    private final Logger logger;

    public Server() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        logger = Logger.getLogger("");
        Handler handler = null;
        try {
            handler = new FileHandler("mylog.Log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);

        try {
            AuthService.connect();
            server = new ServerSocket(856);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            logger.log(Level.INFO, "Сервер запущен!");
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                logger.log(Level.INFO, "Клиент подключился!");
                new ClientHandler(this, socket, logger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка отключения сокета!");
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка отключения сервера!");
                e.printStackTrace();
            }
            logger.log(Level.SEVERE, "Сервер отключился!");
            AuthService.disconnect();
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
        }
        logger.log(Level.INFO, "Клиент написал сообщение участникам чата! Сервер разослал сообщение клиента участникам чата!");
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public int getIdByNick(String nick){
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return o.getId();
            }
        }
        return 0;
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }


    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientsList();
    }
}
