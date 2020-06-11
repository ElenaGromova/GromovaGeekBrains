package Java_Core_Proff.lesson5.date;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SelectDate {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;
    private static SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws ParseException {
        Date date1, date2;
        //SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr1 = "2020-01-05 10:10:00";
        String dateStr2 = "2020-01-01 11:00:00";

        try {
            connect();
            int count = selectCount();
            System.out.println("В бд date всего " + count + " записей");
            for (int i = 1; i <= count; i++) {
                selectData(dateStr1, i);
                selectData(dateStr2, i);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:date.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void selectData(String date, int id) throws SQLException, ParseException {
        Date date1 = formatForDate.parse(date);
        //System.out.println("Дата для поиска вхождения в интервал БД " + date1);
        String query = "SELECT start, end FROM dates WHERE id = " + id;
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()){
            String dateStartSTR = rs.getString("start");
            String dateEndSTR = rs.getString("end");
            Date dateStart = formatForDate.parse(dateStartSTR);
            Date dateEnd = formatForDate.parse(dateEndSTR);
            if ((!date1.before(dateStart)) & (!date1.after(dateEnd))) {
                System.out.println("Запись номер " + id + " БД с интервалом, куда попадает дата " + date + ": [" + dateStartSTR + " , " + dateEndSTR + "]");
            }
        }
    }

    static int selectCount() throws SQLException{
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM dates");
        rs.next();
        return rs.getInt(1);
    }

}


