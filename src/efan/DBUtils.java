package efan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtils {

    private String url = "jdbc:mysql://localhost/epanbank";
    private String username = "root";
    private String password = "pantek";

    public void createDatabase(){
        String sql = "buat database bank";

        try (Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = con.prepareStatement(sql);
            statement.execute();
            System.out.println("Database checked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
