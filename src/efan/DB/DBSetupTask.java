package efan.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * under construction
 * Create table if there is no table
 */
public class DBSetupTask extends DBTask {

    @Override
    protected Void call() throws Exception {

        DatabaseUtil dataSource = DatabaseUtil.getInstance();
        try (Connection conn = dataSource.getConnection()) {
            if(!schemaIsExists(conn)){
                // make a schema
            }
        }
        return null;
    }

    private boolean schemaIsExists(Connection con){
        try (Statement statement = con.createStatement()) {
            statement.executeQuery("SELECT COUNT(*) FROM users");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
