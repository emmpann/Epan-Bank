package efan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import efan.DBUtils;
import efan.session.UserSession;

public class EpanBankRepositoryImpl implements EpanBankRepository{

    Connection conn;

    Statement statement;

    PreparedStatement ps;

    ResultSet rs;

    boolean result;

    String sql;

    String username;

    @Override
    public void addAccount(String username) {

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
    public void addMoney() {
        try {
            conn = DBUtils.getConnection();
            sql = "UPDATE users SET money = money + ? WHERE username=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, UserSession.getAmountOfMoneyAdded());
            ps.setString(2, UserSession.getUsername());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("terjadi eror : " + e);
        }
    }

    @Override
    public void removeMoney() {
        try {
            conn = DBUtils.getConnection();
            sql = "UPDATE users SET money = money - ? WHERE username=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, UserSession.getAmountOfMoneyWithdraw());
            ps.setString(2, UserSession.getUsername());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("terjadi eror : " + e);
        }
    }
    

    @Override
    public void update() {
        try {
            conn = DBUtils.getConnection();
            sql = "UPDATE users SET money = money + ? WHERE username=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, UserSession.getMoney());
            ps.setString(2, UserSession.getUsername());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("terjadi eror : " + e);
        }
    }
    
    @Override
    public boolean checkUser(String username) {
        
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            //result = ps.execute();
            rs = ps.executeQuery();

            if(rs.next()){
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * to know how much money is there in account
     */
    @Override
    public int getMoney() {
        try {
            conn = DBUtils.getConnection();
            sql = "SELECT money FROM users WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, UserSession.getUsername());
            rs = ps.executeQuery();
            
            if(rs.next()){
                UserSession.setMoney(rs.getInt("money"));
            }

        } catch (Exception e) {
            System.out.println("terjadi eror : " + e);   
        }
        return 0;
    }
}
