package efan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import efan.DBUtils;

public class EpanBankRepositoryImpl implements EpanBankRepository{

    Connection conn;

    Statement statement;

    PreparedStatement ps;

    ResultSet rs;

    boolean result;

    String sql;

    String username;

    @Override
    public void add(String username) {

        this.username = username;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("INSERT INTO users (username, money) VALUE (?, ?)");
            ps.setString(1, username);
            ps.setInt(2, 20);

            ps.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove() {
        
    }

    @Override
    public void update() {
        
        
    }

    @Override
    public boolean checkUser(String username) {
        
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            result = ps.execute();

            if(result){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
