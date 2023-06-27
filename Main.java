package sam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*GROUP TWO MEMBERS.... REG #
TAMANDA CHIDOTHE _      BICT0321
MONICA KAMUDE _         BICT0921
REJOICE MIKUNDI _       BSDS1921
CHAUNCY MILLS _         BEDICT 1321
SAMUEL CHIZUMA _        BSDS0821
BRIGHT PAIPILA _        BSDS2421
IMRAN YUSUF SHAIBU _    BICT3221

* */

// Represents a book object
class Book {
    private String author; // Author of the book
    private String title; // Title of the book
    private String ISBN; // ISBN number of the book
    private int publicationYear; // Year of publication
    private int availableCopies; // Number of copies available
    private int totalCopies; // Total number of copies

    // Constructor to initialize the book object
    public Book(String author, String title, String ISBN, int publicationYear, int totalCopies) {
        this.author = author;
        this.title = title;
        this.ISBN = ISBN;
        this.publicationYear = publicationYear;
        this.availableCopies = totalCopies;
        this.totalCopies = totalCopies;
    }

    // Getters to access the book properties
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }


    // Return a string representation of the book
    @Override
    public String toString() {
        return title + " by " + author;
    }
}

// Represents a library object
class Library {
    private String name; // Name of the library
    private ArrayList<Book> books; // List of books in the library

    // Constructor to initialize the library object
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    // Getter to access the library name
    public String getName() {
        return name;
    }

    // Getter to access the list of books
    public ArrayList<Book> getBooks() {
        return books;
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove a book from the library
    public void removeBook(Book book) {
        books.remove(book);
    }
}

// Represents the GUI window for the library
class LibraryGUI extends JFrame {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    private Library library; // The library object
    private DefaultListModel<Book> bookListDetails; // Model for the book list
    private JList<Book> books; // List of books
    private JButton addButton; // Button to add a book
    private JButton removeButton; // Button to remove a book
    private JTextArea detailsOfTheSelectedBook; // Text area to display book details
    
    

    // Constructor to initialize the library GUI
    public LibraryGUI(Library library) {
        this.library = library;
        bookListDetails = new DefaultListModel<>();
        books = new JList<>(bookListDetails);
        addButton = new JButton("Add Book");
        removeButton = new JButton("Remove Book");
        detailsOfTheSelectedBook = new JTextArea(10, 30);

        setTitle("Group Two Library Management System");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the GUI components and add them to the window
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Create book list panel
        JPanel bookListPanel = new JPanel(new BorderLayout());
        bookListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        bookListPanel.add(new JScrollPane(books), BorderLayout.CENTER);
        container.add(bookListPanel, BorderLayout.WEST);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        container.add(buttonPanel, BorderLayout.SOUTH);
        
       
        // Create details panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        detailsPanel.add(new JScrollPane(detailsOfTheSelectedBook), BorderLayout.CENTER);
        container.add(detailsPanel, BorderLayout.CENTER);

        // Set up event listeners for button clicks and book selections
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addBook();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                removeBook();
            }
        });

        books.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                Book selectedBook = books.getSelectedValue();
                if (selectedBook != null) {
                    displayBookDetails(selectedBook);
                } else {
                    clearBookDetails();
                }
            }
        });

        setVisible(true);
    }

    // Display a dialog box to add a new book
    private void addBook() {
        JTextField authorField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField isbnField = new JTextField();
        JTextField publicationYearField = new JTextField();
        JTextField totalCopiesField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("ISBN:"));
        inputPanel.add(isbnField);
        inputPanel.add(new JLabel("Publication Year:"));
        inputPanel.add(publicationYearField);
        inputPanel.add(new JLabel("Total Copies:"));
        inputPanel.add(totalCopiesField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add a Book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String author = authorField.getText();
            String title = titleField.getText();
            String isbn = isbnField.getText();
            int publicationYear = Integer.parseInt(publicationYearField.getText());
            int totalCopies = Integer.parseInt(totalCopiesField.getText());

            Book newBook = new Book(author, title, isbn, publicationYear, totalCopies);
            library.addBook(newBook);
            bookListDetails.addElement(newBook);
        }
    }

    // Remove the selected book from the library and the book list
    private void removeBook() {
        Book selectedBook = books.getSelectedValue();
        if (selectedBook != null) {
            library.removeBook(selectedBook);
            bookListDetails.removeElement(selectedBook);
        }
    }

    // Display the details of the selected book in the text area
    private void displayBookDetails(Book book) {
        detailsOfTheSelectedBook.setText("Author: " + book.getAuthor() + "\n"
                + "Title: " + book.getTitle() + "\n"
                + "ISBN: " + book.getISBN() + "\n"
                + "Publication Year: " + book.getPublicationYear() + "\n"
                + "Available Copies: " + book.getAvailableCopies() + "\n"
                + "Total Copies: " + book.getTotalCopies());
    }

    // Clear the text area when no book is selected
    private void clearBookDetails() {
        detailsOfTheSelectedBook.setText("");
    }
}

// Main class to run the library management system
public class Main {
    public static void main(String[] args) {
        Library library = new Library("Group Two Library");
        LibraryGUI libraryGUI = new LibraryGUI(library);
    }
}
