package efan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import efan.DBUtils;
import efan.model.Account;
import efan.model.User;

public class EpanBankRepositoryImpl implements EpanBankRepository{

    private DBUtils dataSource;

    public EpanBankRepositoryImpl(DBUtils dataSource){
        this.dataSource = dataSource;
    }

    /**
     * make new account
     */
    // @Override
    // public void addAccount(String username) {

    //     this.username = username;

    //     try {
    //         conn = dataSource.getConnection();
    //         ps = conn.prepareStatement("INSERT INTO users (username, money) VALUE (?, ?)");
    //         ps.setString(1, username);
    //         ps.setInt(2, 20);

    //         ps.execute();


    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // @Override
    // public void addMoney() {
    //     try {
    //         conn = dataSource.getConnection();
    //         sql = "UPDATE users SET money = money + ? WHERE username=?";
    //         ps = conn.prepareStatement(sql);
    //         ps.setInt(1, UserSession.getAmountOfMoneyAdded());
    //         ps.setString(2, UserSession.getUsername());
    //         ps.executeUpdate();

    //     } catch (Exception e) {
    //         System.out.println("terjadi eror : " + e);
    //     }
    // }

    // @Override
    // public void removeMoney() {
    //     try {
    //         conn = dataSource.getConnection();
    //         sql = "UPDATE users SET money = money - ? WHERE username=?";
    //         ps = conn.prepareStatement(sql);
    //         ps.setInt(1, UserSession.getAmountOfMoneyWithdraw());
    //         ps.setString(2, UserSession.getUsername());
    //         ps.executeUpdate();

    //     } catch (Exception e) {
    //         System.out.println("terjadi eror : " + e);
    //     }
    // }
    

    // @Override
    // public void update() {
    //     try {
    //         conn = DBUtils.getConnection();
    //         sql = "UPDATE users SET money = money + ? WHERE username=?";
    //         ps = conn.prepareStatement(sql);
    //         ps.setInt(1, UserSession.getMoney());
    //         ps.setString(2, UserSession.getUsername());
    //         ps.executeUpdate();

    //     } catch (Exception e) {
    //         System.out.println("terjadi eror : " + e);
    //     }
    // }
    
    // /**
    //  * to know how much money is there in account
    //  */
    // @Override
    // public int getMoney() {
    //     try {
    //         conn = DBUtils.getConnection();
    //         sql = "SELECT money FROM users WHERE username = ?";
    //         ps = conn.prepareStatement(sql);
    //         ps.setString(1, UserSession.getUsername());
    //         rs = ps.executeQuery();
            
    //         if(rs.next()){
    //             UserSession.setMoney(rs.getInt("money"));
    //         }

    //     } catch (Exception e) {
    //         System.out.println("terjadi eror : " + e);   
    //     }
    //     return 0;
    // }
    
    // @Override
    // public boolean usernameCheck() {
        
    //     try {
    //         conn = DBUtils.getConnection();
    //         ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
    //         ps.setString(1, UserSession.getUsername());
    //         //result = ps.execute();
    //         rs = ps.executeQuery();
    
    //         if(rs.next()){
    //             return true;
    //         }
    
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    // @Override
    // public boolean passwordCheck() {
    //     try {
    //         conn = DBUtils.getConnection();
    //         ps = conn.prepareStatement("SELECT * FROM users WHERE password = ?");
    //         ps.setString(1, UserSession.getPassword());
    //         //result = ps.execute();
    //         rs = ps.executeQuery();
    
    //         if(rs.next()){
    //             return true;
    //         }
    
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }
    
    @Override
    public boolean loginCustomer(String username, String password) {
        
        String sql = "SELECT username, password, email, id FROM users WHERE username = ? AND password = ?";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("id")).login();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isAvailableCustomer(User newUser){
        
        String sql = "SELECT username, email FROM users WHERE username = ? OR email = ?";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getEmail());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                newUser.setUsername(rs.getString("username"));
                newUser.setEmail(rs.getString("email"));
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addCustomer(User newUser) {

        if(!isAvailableCustomer(newUser)){

            // Add to database
            String sql = "INSERT INTO users (username, password, email) VALUE (?, ?, ?)";
            try {
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, newUser.getUsername());
                ps.setString(2, newUser.getPassword());
                ps.setString(3, newUser.getEmail());
                if(!ps.execute()){ // ps.execute will return 0 if the elements succes inserted
                    return true;
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }

    
    @Override
    public boolean isAvailableAccount() {
        System.out.println("memanggil isavailableaccount");
        User customer = User.getUserInstance();
        String sql = "SELECT fullName, address, accountNumber, pin, balance, userId FROM accounts WHERE userId = ?";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, customer.getId());
            System.out.println("id : " + customer.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                new Account(rs.getString("fullName"), rs.getString("address"), rs.getInt("pin"), rs.getString("accountNumber"), rs.getInt("balance"), rs.getInt("userId")).takeAccount();
                return true;
            }

        } catch (Exception e) {
            System.out.println("terdapat error di repository");
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean openAccount(Account newAccount){

        String sql = "INSERT INTO accounts (fullName, address, pin, accountNumber, userId) VALUE (?, ?, ?, ?, ?) ";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newAccount.getFullName());
            ps.setString(2, newAccount.getAddress());
            ps.setInt(3, newAccount.getPin());
            ps.setString(4, newAccount.getAccountNumber());
            ps.setInt(5, newAccount.getUserId());

            if(!ps.execute()){
                return true;
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("terdapat error di repository");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addMoney() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void removeMoney() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int getMoney() {
        // TODO Auto-generated method stub
        return 0;
    }
}
