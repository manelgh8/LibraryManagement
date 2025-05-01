 package factory;  
 
import model.User;
import model.Admin;
import model.Reader;

public class UserFactory {
    public User createUser(String role, String name, String id, String password) {
        if (role.equalsIgnoreCase("admin")) {
            return new Admin(id, name, password);
        } else {
            return new Reader(id, name, password);
        }
    }
}
