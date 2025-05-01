package Main;

import model.User;
import model.Book;
import system.Library;
import facade.LibraryFacade;
import javax.swing.*;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
 
 
public class Main {
    static LibraryFacade facade = new LibraryFacade();
   
    public static void main(String[] args) {
        preloadBooks();
        showShoose();
    }
    static void showShoose() {
        User user  = new User("AdminTest", "admin123", "admin");
        JFrame menuFrame = new JFrame("Library System - Main Menu");
        menuFrame.setSize(300, 200);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new FlowLayout());

        JButton loginBtn = new JButton("Admin");
        JButton signupBtn = new JButton("Reader");
        
        loginBtn.addActionListener(e -> {
            

            menuFrame.dispose();
            adminTache(user);
        });

        signupBtn.addActionListener(e -> {
            menuFrame.dispose();
            showMainMenu();
        });
        menuFrame.setBounds(300, 200, 300, 300);
        menuFrame.add(loginBtn);
        menuFrame.add(signupBtn);
        menuFrame.setVisible(true);
    }

    static void adminTache(User user) {
       
    JFrame dash = new JFrame("Library Dashboard - " + user.name + " (" + user.name + ")");
    dash.setSize(500, 400);
    dash.setLayout(new BorderLayout());

    JTextField isbnField = new JTextField();
    JTextArea output = new JTextArea();
    output.setEditable(false);

    JButton borrowBtn = new JButton("Borrow");
    JButton returnBtn = new JButton("Return");

    borrowBtn.setBackground(new Color(241, 196, 15));
    returnBtn.setBackground(new Color(231, 76, 60));
    returnBtn.setForeground(Color.WHITE);

    JPanel top = new JPanel(new GridLayout(2, 2));
    top.add(new JLabel("ISBN:"));
    top.add(isbnField);
    top.add(borrowBtn);
    top.add(returnBtn);

    dash.add(top, BorderLayout.NORTH);
    dash.add(new JScrollPane(output), BorderLayout.CENTER);

    borrowBtn.addActionListener(e -> {
        facade.borrowBooks(user, List.of(isbnField.getText()));
        output.append("📚 Borrowed: " + isbnField.getText() + "\n");
    });

    returnBtn.addActionListener(e -> {
        facade.returnBooks(user, List.of(isbnField.getText()));
        output.append("📦 Returned: " + isbnField.getText() + "\n");
    });

    // 👑 Admin section
  //  if ("admin".equalsIgnoreCase(user.name)) {
        JButton addBookBtn = new JButton("➕ Add Book");
        JButton removeBookBtn = new JButton("🗑 Remove Book");
        JButton viewUsersBtn = new JButton("👥 View Users");
        JButton viewBooksBtn = new JButton("📚 View Books");

        addBookBtn.setBackground(new Color(52, 152, 219));
        removeBookBtn.setBackground(new Color(155, 89, 182));
        viewUsersBtn.setBackground(new Color(26, 188, 156));
        viewBooksBtn.setBackground(new Color(52, 73, 94));
        viewBooksBtn.setForeground(Color.decode("#D09305"));


        JPanel adminPanel = new JPanel();
        adminPanel.add(addBookBtn);
        adminPanel.add(removeBookBtn);
        adminPanel.add(viewUsersBtn);
        adminPanel.add(viewBooksBtn);
        dash.add(adminPanel, BorderLayout.SOUTH);

        viewBooksBtn.addActionListener(e -> {
            output.append("📚 Books:\n");
            for (Book b : Library.getInstance().books) {
                output.append(" - " + b.getTitle() + " by " + b.getAuthor() + " (ISBN: " + b.getIsbn() + ")\n");
            }
        });

        addBookBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(dash, "Enter book title:");
            String author = JOptionPane.showInputDialog(dash, "Enter author:");
            String isbn = JOptionPane.showInputDialog(dash, "Enter ISBN:");
            if (title != null && author != null && isbn != null) {
                Library.getInstance().books.add(new Book(isbn, title, author));
                output.append("✅ Book added: " + title + "\n");
            }
        });

        removeBookBtn.addActionListener(e -> {
            String isbn = JOptionPane.showInputDialog(dash, "Enter ISBN to remove:");
           // boolean removed = Library.getInstance().books.remove(b -> b.getIsbn().equals(isbn));
           // output.append(removed ? "❌ Book removed.\n" : "⚠️ Book not found.\n");
        });

        viewUsersBtn.addActionListener(e -> {
            output.append("👥 Users:\n");
            for (User u : Library.getInstance().users) {
                output.append(" - " + u.id + " (" + u.name + ")\n");
            }
        });
  //  }
    dash.setBounds(200, 80, 700, 500);
    
    dash.setVisible(true);
    }


    static void showMainMenu() {
        JFrame menuFrame = new JFrame("Library System - Main Menu");
        menuFrame.setSize(300, 200);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new FlowLayout());
        JLabel l1=new JLabel("Library System ");  
        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");
        JLabel l2 = new JLabel("Name :");
        JTextField nameField = new JTextField();
        JTextField textField = new JTextField();
        JTextField passField = new JTextField();
        JLabel l3 = new JLabel("password :");
        JLabel l4 = new JLabel("Email :");

        l1.setBounds(180,50, 150,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16)); 
        l1.setForeground(Color.decode("#D09305"));

        l2.setFont(new Font("Arial", Font.BOLD, 16)); 
        l2.setBounds(40, 90, 100, 50);
        nameField.setBounds(150,100,160,30);
        
        l3.setFont(new Font("Arial", Font.BOLD, 16)); 
        l3.setBounds(40,140,100,50);
        textField.setBounds(150,150,160,30);
        
        l4.setFont(new Font("Arial", Font.BOLD, 16)); 
        l4.setBounds(40,190,100,50);
        passField.setBounds(150,200,160,30);


        loginBtn.setBackground(Color.decode("#D09305"));
        loginBtn.setBounds(150, 300, 160, 40);   

        signupBtn.setBackground(Color.decode("#D09305"));
        signupBtn.setBounds(150, 250, 160, 40);   
        
        loginBtn.addActionListener(e -> {
            menuFrame.dispose();
            showLoginForm();
        });

        /*signupBtn.addActionListener(e -> {
            menuFrame.dispose();
            showSignupForm();
        });*/
        signupBtn.addActionListener(e -> {
            User user = facade.signUp(nameField.getText(), passField.getText(), passField.getText());
            menuFrame.dispose();
            showDashboard(user);
        });


        menuFrame.add(nameField);
        menuFrame.add(textField);
        menuFrame.add(l2);
        menuFrame.add(l3);
        menuFrame.add(l4);
        menuFrame.add(passField);

        menuFrame.add(l1,BorderLayout.CENTER);
        menuFrame.add(loginBtn,BorderLayout.CENTER);
        menuFrame.add(signupBtn,BorderLayout.CENTER);
        menuFrame.getContentPane().setBackground(Color.decode("#EFCA75"));
        menuFrame.setLayout(new BorderLayout());  
        menuFrame.setBounds(300, 100, 500, 500);
       // menuFrame.setSize(500, 500);  
        menuFrame.setVisible(true);
    }




    static void showLoginForm() {
        JFrame menuFrame = new JFrame("Library System -LogIn");
        menuFrame.setSize(300, 200);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new FlowLayout());
        JLabel l1=new JLabel("Library System ");  
        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");
        
        JTextField nameField = new JTextField();
        
        JTextField passField = new JTextField();
        JLabel l3 = new JLabel("Name :");
        JLabel l4 = new JLabel("Password :");
        JLabel status = new JLabel("");

        l1.setBounds(180,50, 150,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16)); 
        l1.setForeground(Color.decode("#D09305"));

        l3.setFont(new Font("Arial", Font.BOLD, 16)); 
        l3.setBounds(40,90,100,50);
        nameField.setBounds(150,100,160,30);
        
        l4.setFont(new Font("Arial", Font.BOLD, 16)); 
        l4.setBounds(40,140,100,50);
        passField.setBounds(150,150,160,30);

        loginBtn.setBackground(Color.decode("#D09305"));
        loginBtn.setBounds(150, 200, 160, 40);   

        signupBtn.setBackground(Color.decode("#D09305"));
        signupBtn.setBounds(150, 250, 160, 40);   
        menuFrame.add(status);
        loginBtn.addActionListener(e -> {
            User user = facade.login(nameField.getText(), passField.getText());
            menuFrame.dispose();
            showDashboard(user);
           /* if (user != null) {
                menuFrame.dispose();
                showDashboard(user);
            } else {
                status.setText("❌ Invalid login.");
            }*/
        });
        signupBtn.addActionListener(e -> {
            menuFrame.dispose();
            showMainMenu();
        });

       
        menuFrame.add(nameField);
        
       
        menuFrame.add(l3);
        menuFrame.add(l4);
        menuFrame.add(passField);

        menuFrame.add(l1,BorderLayout.CENTER);
        menuFrame.add(loginBtn,BorderLayout.CENTER);
        menuFrame.add(signupBtn,BorderLayout.CENTER);
        menuFrame.getContentPane().setBackground(Color.decode("#EFCA75"));
        menuFrame.setLayout(new BorderLayout());  
        menuFrame.setBounds(300, 100, 500, 500);
       // menuFrame.setSize(500, 500);  
        menuFrame.setVisible(true);
    }
      
  
    static void showDashboard(User user) {
        JFrame menuFrame = new JFrame("Library Dashboard -");
        menuFrame.setLayout(null); // Using absolute positioning
    
        JLabel isbnT = new JLabel("ISBN:");
        JTextField isbnField = new JTextField();
        JTextArea output = new JTextArea();
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
    
        JButton borrowBtn = new JButton("Borrow");
        JButton returnBtn = new JButton("Return");
    
        
        isbnT.setBounds(50, 90, 100, 30);
        isbnField.setBounds(150, 90, 160, 30);
        borrowBtn.setBounds(100, 150, 100, 30);
        returnBtn.setBounds(250, 150, 100, 30);
        scrollPane.setBounds(50, 200, 400, 100); 
    
        borrowBtn.setBackground(Color.decode("#D09305"));
        returnBtn.setBackground(Color.decode("#D09305"));
    
        
        menuFrame.add(isbnT);
        menuFrame.add(isbnField);
        menuFrame.add(borrowBtn);
        menuFrame.add(returnBtn);
        menuFrame.add(scrollPane); 
    
        
        menuFrame.getContentPane().setBackground(Color.decode("#EFCA75"));
        menuFrame.setBounds(300, 100, 500, 500);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    
        
        borrowBtn.addActionListener(e -> {
            facade.borrowBooks(user, List.of(isbnField.getText()));
            output.setForeground(Color.BLUE);
            output.append("📚 Borrowed: " + isbnField.getText() + "\n");
        });
    
        returnBtn.addActionListener(e -> {
            facade.returnBooks(user, List.of(isbnField.getText()));
            output.setForeground(Color.GREEN);
            output.append("📦 Returned: " + isbnField.getText() + "\n");
        });
    }
    
       
    
    

/* 
    static void showDashboard(User user) {
        JFrame menuFrame = new JFrame("Library Dashboard -");
        JTextField isbnField = new JTextField();
        JLabel isbnT = new JLabel("ISBN :");
        JTextArea output = new JTextArea();
        output.setEditable(false);

        JButton borrowBtn = new JButton("Borrow");
        JButton returnBtn = new JButton("Return");
        borrowBtn.setBackground(Color.green);
        returnBtn.setBackground(Color.green);
        
        JPanel top = new JPanel(new GridLayout(2, 2));
       
        top.add(isbnField);
        top.add(borrowBtn);
        top.add(returnBtn);

        isbnT.setFont(new Font("Arial", Font.BOLD, 16));
        isbnT.setBounds(50,90, 100,50);
        isbnField.setBounds(150,100,160,30);

        borrowBtn.setBackground(Color.decode("#D09305"));
        borrowBtn.setBounds(100,150,100,30);
        returnBtn.setBackground(Color.decode("#D09305"));
        returnBtn.setBounds(250,150,100,30);
        menuFrame.add(top, BorderLayout.NORTH);
        menuFrame.add(new JScrollPane(output), BorderLayout.CENTER);

        borrowBtn.addActionListener(e -> {
            facade.borrowBooks(user, List.of(isbnField.getText()));
            output.setForeground(Color.RED);
            output.append("📚 Borrowed: " + isbnField.getText() + "\n");
        });

        returnBtn.addActionListener(e -> {
            facade.returnBooks(user, List.of(isbnField.getText()));
            output.setForeground(Color.RED);
            output.append("📦 Returned: " + isbnField.getText() + "\n");
        });
        menuFrame.add(isbnT);
        menuFrame.add(isbnField);
        menuFrame.add(borrowBtn);
        menuFrame.add(returnBtn);
        menuFrame.getContentPane().setBackground(Color.decode("#EFCA75"));
        menuFrame.setLayout(new BorderLayout());  
        menuFrame.setBounds(300, 100, 500, 500);
       // menuFrame.setSize(500, 500);  
        menuFrame.setVisible(true);
    }*/

    static void preloadBooks() {
        Library lib = Library.getInstance();
    
        // Add some books
        lib.books.add(new Book("123", "The Alchemist", "Paulo Coelho"));
        lib.books.add(new Book("456", "Clean Code", "Robert Martin"));
        lib.books.add(new Book("001", "The Alchemist", "Paulo Coelho"));
        lib.books.add(new Book("002", "Clean Code", "Robert C. Martin"));
        lib.books.add(new Book("003", "The Pragmatic Programmer", "Andrew Hunt & David Thomas"));
        lib.books.add(new Book("004", "Introduction to Algorithms", "Thomas H. Cormen"));
        lib.books.add(new Book("005", "1984", "George Orwell"));
        lib.books.add(new Book("006", "To Kill a Mockingbird", "Harper Lee"));
        lib.books.add(new Book("007", "One Piece Vol. 1", "Eiichiro Oda"));
        lib.books.add(new Book("008", "Naruto Vol. 1", "Masashi Kishimoto"));
        lib.books.add(new Book("009", "Haikyu!! Vol. 1", "Haruichi Furudate"));
        lib.books.add(new Book("010", "Hajime no Ippo Vol. 1", "George Morikawa"));
        lib.books.add(new Book("011", "The Art of War", "Sun Tzu"));
        lib.books.add(new Book("012", "The Catcher in the Rye", "J.D. Salinger"));
        
        // ✅ Add some test users
        lib.users.add(new User("1", "Ippo Makunouchi", "admin123"));
        lib.users.add(new User("2", "ricardo", "pass123"));
        lib.users.add(new User("3", "volg", "pass456"));
        lib.users.add(new User("4", "sakamoto", "admin123"));
        lib.users.add(new User("5", "Sendo Takeshi", "pass123"));
        lib.users.add(new User("6", "Miyata Ichiro", "pass456"));
        lib.users.add(new User("7", "Takamura Mamoru", "admin123"));
        lib.users.add(new User("8", "Mashiba Ryo", "pass123"));
        lib.users.add(new User("9", "date", "pass456"));
        lib.users.add(new User("10", "HinataShoyo", "reader"));
        lib.users.add(new User("11", "KageyamaTobio", "reader"));
        lib.users.add(new User("12", "OikawaTooru", "reader"));
        lib.users.add(new User("13", "TsukishimaKei", "reader"));
        lib.users.add(new User("14", "NishinoyaYuu", "reader"));
        lib.users.add(new User("15", "KurooTetsurou", "reader"));
        lib.users.add(new User("16", "KurooTetsurou", "reader"));
        lib.users.add(new User("18", "AkaashiKeiji", "reader"));
        lib.users.add(new User("19", "UshijimaWakatoshi", "reader"));
        lib.users.add(new User("20", "KenmaKozume", "reader"));
        
        lib.users.add(new User("21", "AokiMasaru", "reader"));
        lib.users.add(new User("22", "KimuraTatsuya", "reader"));
        lib.users.add(new User("23", "ItagakiManabu", "reader"));
        lib.users.add(new User("24", "KamogawaGenji", "reader"));
        lib.users.add(new User("25", "YamadaNaomichi", "reader")); // Aoki’s full name
        lib.users.add(new User("26", "Umezawa", "reader"));
        lib.users.add(new User("27", "IimuraMari", "reader")); // Reporter
        lib.users.add(new User("28", "Hoshi", "reader"));       // Kamogawa gym coach
        lib.users.add(new User("29", "DateEiji", "reader"));
        lib.users.add(new User("30", "SawamuraRyuhei", "reader"));
    }
       
    
}
