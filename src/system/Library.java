package system;

import model.Book;
import model.User;

import java.util.ArrayList;

public class Library {
    private static  Library instance;
     public ArrayList<Book> books = new ArrayList<>();
     public ArrayList<User> users = new ArrayList<>();
    private Library() {}
    public static Library getInstance(){
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }
    public void loadBooksFromCsv(){

    }
    public void loadUsersFromCsv(){

    }
    public void saveUsersToCsv(){

    }
    public void saveBooksToCsv(){

    }
    public void borrowBook(User user,String isbn){
        System.out.println(user.name + " borrowed book with ISBN " + isbn);
    }
    public void returnBook(User user,String isbn){
        System.out.println(user.name + " returned book with ISBN " + isbn);
    }
}
