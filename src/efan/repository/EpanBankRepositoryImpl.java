package efan.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import efan.DB.DBTask;
import efan.DB.DatabaseUtil;
import efan.model.Account;
import efan.model.User;

public class EpanBankRepositoryImpl implements EpanBankRepository{

    private static CountDownLatch lock1 = new CountDownLatch(1);
    private static CountDownLatch lock2 = new CountDownLatch(1);

    public static void resetLock() {
        lock1 = new CountDownLatch(1);
        lock2 = new CountDownLatch(1);
    }

    public static void resetLock1() {
        lock1 = new CountDownLatch(1);
    }

    public static CountDownLatch getLock1() {
        return lock1;
    }

    public static CountDownLatch getLock2() {
        return lock2;
    }
    
    static private ExecutorService executorService;

    public static void setExecutorService(ExecutorService e) {
        executorService = e;
    }

    public static ExecutorService getExecutorServiceInstace() {
        return executorService;
    }

    private DatabaseUtil dataSource;

    public EpanBankRepositoryImpl(DatabaseUtil dataSource){
        this.dataSource = dataSource;
    }

    /**
     * BACKGROUND TASK
     */

    public class LoginCustomerTask extends DBTask<Boolean> {

        final String username, password;
        
        public LoginCustomerTask(final String username, final String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Boolean call() throws Exception {
            return loginCustomer(username, password);
        }
    }
    
    public class IsAvailableAccountTask extends DBTask<Boolean> {
        
        @Override
        protected Boolean call() throws Exception {
            return isAvailableAccount();
        }
    }
    
    public class AddMoneyTask extends DBTask<Boolean> {

        final private int  balance;
        final private Account account;

        public AddMoneyTask(int balance, Account account) {this.balance = balance; this.account = account;}

        @Override
        protected Boolean call() throws Exception {
            return addMoney(balance, account);
        }
    }

    public class RemoveMoneyTask extends DBTask<Boolean> {

        final private int  balance;
        final private Account account;

        public RemoveMoneyTask(int balance, Account account) {this.balance = balance; this.account = account;} 

        @Override
        protected Boolean call() throws Exception {
            return removeMoney(balance, account);
        }
    }

    public class GetBalanceTask extends DBTask<Integer> {
        @Override
        protected Integer call() throws Exception {return getBalance();}
    }

    /**
     * BUSSINES LOGIC QUERING TO DATABASE
     */

    @Override
    public boolean loginCustomer(String username, String password) {

        // have to fix the bussines logic, problems: the username and password are ignorecase, its have to sensitive case
        String sql = "SELECT username, password, email, id FROM users WHERE username = ? AND password = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("id")).login();
                lock2.countDown();
                
                lock1.await();
                return true;
            } else { 
                lock2.countDown();
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
            System.out.println("error isAvailableCustomer");
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

        String sql = "SELECT id, fullName, address, accountNumber, pin, balance, userId FROM accounts WHERE userId = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {
            lock2.await();
            User customer = User.getUserInstance();
            
            ps.setInt(1, customer.getId());

            System.out.println("id : " + customer.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                // Taking account
                new Account(rs.getInt("id"), rs.getString("fullName"), rs.getString("address"), rs.getInt("pin"), rs.getString("accountNumber"), rs.getInt("balance"), rs.getInt("userId")).takeAccount();
                lock1.countDown();
                return true;
            }

        } catch (Exception e) {
            System.out.println("terdapat error di repository:isavailable account");
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean openAccount(Account newAccount){

        String sql = "INSERT INTO accounts (fullName, address, pin, accountNumber, userId) VALUE (?, ?, ?, ?, ?) ";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {

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
    public boolean addMoney(int balanceAdded, Account account) {

        String sql = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            
            ps.setInt(1, balanceAdded);
            ps.setString(2, account.getAccountNumber());

            ps.executeUpdate();
            lock1.countDown();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean removeMoney(int balanceRemoved, Account account) {

        String sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            
            ps.setInt(1, balanceRemoved);
            ps.setInt(2, account.getId());

            ps.executeUpdate();
            lock1.countDown();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean transferMoney(int money, Account sender, Account target) {
        // TODO Auto-generated method stub
        // 1. add money to user id target
        // 2. remove money from user id sender
        
        try {
            removeMoney(money, sender);
            addMoney(money, target);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int getBalance() {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);) {
            
            lock1.await();
            ps.setInt(1, Account.getAccountInstance().getId());
                
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Account.getAccountInstance().setBalance(rs.getInt("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Account getAccountInfo(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    // @Override
    // public Account getAccountInfo(int id) {
    //     // TODO Auto-generated method stub
    //     String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
    //     try {
    //         Connection conn = dataSource.getConnection();
    //         PreparedStatement ps = conn.prepareStatement(sql);
    //         ps.setString(1, username);
    //         ps.setString(2, password);
            
    //         ResultSet rs = ps.executeQuery();

    //         if(rs.next()){
    //             return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("id")).login();
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return this;
    // }

}
