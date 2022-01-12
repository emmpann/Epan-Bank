package efan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {

    private static String url = "jdbc:mysql://localhost/epanbank";
    private static String username = "root";
    private static String password = "pantek";

    public static void createDatabase(){
        String sql = "buat database bank";

        try (Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
            System.out.println("Database checked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
