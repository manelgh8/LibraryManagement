package command;

import model.User;
import system.Library;

public class ReturnCommand implements Command{
    User user;
    String isbn;
    public ReturnCommand(User user, String isbn) {
        this.user = user;
        this.isbn = isbn;
    }
    @Override
    public void execute() {
        Library.getInstance().returnBook(user, isbn);
        }
    
}
