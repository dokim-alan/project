package ca.sait.project.services;

import ca.sait.project.dataaccess.UserDB;
import ca.sait.project.models.User;

/**
 * Login validation service
 * @author Alan(Dong O) Kim
 */
/* old
public class AccountService {
        public User login(String email, String password) {
            if (email.equals("abe") && password.equals("password")) {
                return new User(email, null);
            } else if (email.equals("barb") && password.equals("password")) {
                return new User(email, null);
            } else {
                return null;
            }
        }
}
*/

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
