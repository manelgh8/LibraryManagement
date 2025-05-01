package command;
import model.User;
import system.Library; 
public class BorrowCommand implements Command {
    private User user;
    private String isbn;

    public BorrowCommand(User user, String isbn) {
        this.user = user;
        this.isbn = isbn;
    }

    @Override
    public void execute() {
        Library.getInstance().borrowBook(user, isbn);
    }
}