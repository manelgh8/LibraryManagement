package facade;

import model.User;
import factory.UserFactory;
import system.Library;
import command.BorrowCommand;   
import command.ReturnCommand;
import java.util.List;
import java.util.UUID;

public class LibraryFacade {
    UserFactory userFactory = new UserFactory();
    public User signUp(String name,String password,String role){
        User user = userFactory.createUser(role, name, UUID.randomUUID().toString(), password);
        Library.getInstance().users.add(user);
        return user;
    }
    public User login(String name, String password) {
        for (User user : Library.getInstance().users) {
            if (user.name.equals(name) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void borrowBooks(User user,List<String> isbns){
        for (String isbn : isbns) {
            new BorrowCommand(user, isbn).execute();
        }
    } 
    public void returnBooks(User user,List<String> isbns){
       
        for (String isbn : isbns) {
            new ReturnCommand(user, isbn).execute();
        }

    } 
    public void loadFromCsv(){
        Library.getInstance().loadBooksFromCsv();
        Library.getInstance().loadUsersFromCsv();
    }
    public void saveToCsv(){
        Library.getInstance().saveBooksToCsv();
        Library.getInstance().saveUsersToCsv();
    }
}
