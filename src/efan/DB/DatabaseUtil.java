package efan.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtil {
    
    public static DatabaseUtil databaseUtil = new DatabaseUtil();
    
    /**
     * LOCAL HOST
     */
    //private String url = "jdbc:mysql://localhost/epanbank";
    // private String username = "root";
    // private String password = "pantek";

    /**
     * AZURE MYSQL SERVER
     */
    private String url = "jdbc:mysql://efandbflexserver.mysql.database.azure.com:3306/epanbank_db?useSSL=true";
    private String username = "efandb99";
    private String password = "wishmeluck00@@";

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

    public static DatabaseUtil getInstance() {
        if(databaseUtil == null) {
            databaseUtil = new DatabaseUtil();
        }
        return databaseUtil;
    }
}
