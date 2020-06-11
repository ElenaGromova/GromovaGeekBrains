package Java_Core_Proff.lesson2.lesson2_1;

import java.sql.*;

public class DBWork {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;

    public static void main(String[] args) {
        try {
            connect();

            createTable("logs", "id", "logtext");
            insertData("logs", "logtext", "таблица");
            insertData("logs", "logtext", "логов");
            insertData("logs", "logtext", "подключения");
            insertData("logs", "logtext", "чат");
            updateData("logs", "logtext", "апдейт", 3);
            deleteData("logs", "logtext", "логов");
            selectData("logs", "logtext", 1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            disconnect();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createTable(String nameDB, String id, String field) throws SQLException{
            //stmt = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + nameDB + " ( " +
                    id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    field + " TEXT NOT NULL)";
            stmt.executeUpdate(query);
            System.out.println("База данных создана!");
    }

    static void insertData(String nameDB, String fieldName, String fieldValue) throws SQLException{
        //stmt = connection.createStatement();
        String query = "INSERT INTO " + nameDB + " (" + fieldName + ") " + "VALUES('" + fieldValue + "')";
        stmt.executeUpdate(query);
        System.out.println("Запись добавлена!");
    }

    static void updateData(String nameDB, String fieldName, String fieldValueNew, int id) throws SQLException{
        //stmt = connection.createStatement();
        String query = "UPDATE " + nameDB + " SET " + fieldName + " = '" + fieldValueNew + "' WHERE id = " + id;
        stmt.executeUpdate(query);
        System.out.println("Запись изменена!");
    }

    static void deleteData(String nameDB, String fieldName, String fieldValue) throws SQLException{
        //stmt = connection.createStatement();
        String query = "DELETE FROM " + nameDB + " WHERE " + fieldName + " = '" + fieldValue + "'";
        stmt.executeUpdate(query);
        System.out.println("Запись удалена!");
    }

    static void selectData(String nameDB, String fieldName, int id) throws SQLException{
        //stmt = connection.createStatement();
        String query = "SELECT " + fieldName + " FROM "+ nameDB + " WHERE id = " + id;
        ResultSet rs = stmt.executeQuery(query);
        System.out.print("Записи БД с id = " + id + ": ");
        while (rs.next()){
            System.out.println(rs.getString("logtext") + " ");
        }
    }

}

