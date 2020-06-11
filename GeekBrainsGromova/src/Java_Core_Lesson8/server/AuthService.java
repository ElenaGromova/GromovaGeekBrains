package Java_Core_Lesson8.server;

import java.sql.*;
import java.util.HashSet;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO userslist (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname FROM userslist WHERE login = '" + login + "'" + " and password = '"  + pass + "'");
            if (rs.next()){
                //String nick = rs.getString(1);
               //String password = rs.getString(2);
                return rs.getString("nickname");
            }
//            int myHash = pass.hashCode(); // 137
//            if (rs.next()) {
//                String nick = rs.getString(1);
//                int dbHash = rs.getInt(2);
//                if (myHash == dbHash) {
//                    return nick;
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getNickById(int id) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname FROM userslist WHERE id = " + id);
            if (rs.next()){
                //String nick = rs.getString(1);
                //String password = rs.getString(2);
                return rs.getString("nickname");
            }
//            int myHash = pass.hashCode(); // 137
//            if (rs.next()) {
//                String nick = rs.getString(1);
//                int dbHash = rs.getInt(2);
//                if (myHash == dbHash) {
//                    return nick;
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getIdByNick(String nick) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT id FROM userslist WHERE nickname = '" + nick + "'");
            if (rs.next()){
                //String nick = rs.getString(1);
                //String password = rs.getString(2);
                return rs.getInt("id");
            }
//            int myHash = pass.hashCode(); // 137
//            if (rs.next()) {
//                String nick = rs.getString(1);
//                int dbHash = rs.getInt(2);
//                if (myHash == dbHash) {
//                    return nick;
//                }
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Integer getIdByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT id FROM userslist WHERE login = '" + login + "'" + " and password = '"  + pass + "'");
            if (rs.next()){
                //String nick = rs.getString(1);
                //String password = rs.getString(2);
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void addUserToBlackList(int idSender, int idToBlackList) {
        try {
            String query = "INSERT INTO blacklist (iduser, idblack) VALUES (?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, idSender);
            ps.setInt(2, idToBlackList);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteUserToBlackList(int idSender, int idToBlackList) {
        try {
            String query = "DELETE FROM blacklist WHERE iduser = " + idSender + "and idblack = " + idToBlackList;
            PreparedStatement ps = connection.prepareStatement(query);
            //ps.setInt(1, idSender);
            //ps.setInt(2, idToBlackList);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String checkRegistration(String msg){
        String[] tokens = msg.split(" ");
        String login = tokens[1];
        String password = tokens[2];
        String nick = tokens[3];
        //проверка логина
        try {
            ResultSet rs = stmt.executeQuery("SELECT login FROM userslist WHERE login = '" + login + "'");
            if (rs.next()){
                return "Пользователь с таким логином уже существует!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //проверка ника
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname FROM userslist WHERE nickname = '" + nick + "'");
            if (rs.next()){
                return "Этот никнейм занят!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addUser(login, password, nick);
        return "/regok";
    }

    public static HashSet<String> blackListInit(int id){
        HashSet<String> blackList = new HashSet<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM blacklist WHERE iduser = " + id);
            while (rs.next()){
                int i = rs.getInt("idblack");
                String s = getNickById(i);
                blackList.add(s);
                System.out.println("add " + s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blackList;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
