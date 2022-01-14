package efan.test.UserSession;

import efan.session.UserSession;

public class UserSessionTest {
    public static void main(String[] args) {
        testGetUsername();
    }

    public static void testGetUsername(){
        System.out.println(UserSession.getUsername());
        
    }

}
