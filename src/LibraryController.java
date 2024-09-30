package src;

import java.io.*;
import java.util.Scanner;

public class LibraryController {
    private Book[] books;
    private final int MAX_BOOKS = 100; // Maximum number of books that can be stored
    private final String CSV_FILE = "data/StartingDataFile.csv";
    private String[] headers; // Array to store header names
    private String[][] data;

    // Constructor
    public LibraryController() {
        books = new Book[MAX_BOOKS];
        data = new String[MAX_BOOKS][];
        loadBooksFromCSV();
    }

    // Load books from a CSV file
    private void loadBooksFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                headers = headerLine.split(","); // Store headers
            }

            String line;
            int bookCount = 0;

            while ((line = reader.readLine()) != null && bookCount < MAX_BOOKS) {
                String[] bookData = line.split(",", -1);
                data[bookCount] = bookData; // Store data for each book in the 2D array
                bookCount++;

                // Extract data and create book objects
                String title = getField(bookData, "title", "Unknown Title");
                String isbn = getField(bookData, "isbn", "Unknown ISBN");
                boolean isEbook = getField(bookData, "eBook", "false").equalsIgnoreCase("TRUE");
                int yearPublished = Integer.parseInt(getField(bookData, "year", "0"));
                int edition = Integer.parseInt(getField(bookData, "edition", "Unknown Publisher"));

                // Parse authors (up to 3)
                Author[] authors = new Author[3];

                authors = getAuthorInformation(bookData, authors);

                books[bookCount - 1] = new Book(title, isbn, isEbook, yearPublished, edition, authors);
            }
        } catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    private String getField(String[] bookData, String header, String defaultValue) {
        int index = findHeaderIndex(header); // Find index by searching the header array
        if (index != -1 && index < bookData.length && !bookData[index].isEmpty()) {
            return bookData[index];
        }
        return defaultValue;
    }

    // Method to find the index of a header in the headers array
    private int findHeaderIndex(String header) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(header)) {
                return i;
            }
        }
        return -1; // Return -1 if the header is not found
    }

    private Author[] getAuthorInformation(String[] bookData, Author[] authors) {

        String authorFamilyName;
        String authorFirstName;
        String nationality;
        int yearOfBirth;

        authorFamilyName = getField(bookData, "familyNameOne", null);
        authorFirstName = getField(bookData, "firstNameOne", null);
        nationality = getField(bookData, "nationalityOne", null);
        yearOfBirth = Integer.parseInt(getField(bookData, "birthYearOne", "0"));

        if (authorFamilyName != null) {
            authors[0] = new Author(authorFamilyName, authorFirstName, nationality, yearOfBirth);
        }

        authorFamilyName = getField(bookData, "familyNameTwo", null);
        authorFirstName = getField(bookData, "firstNameTwo", null);
        nationality = getField(bookData, "nationalityTwo", null);
        yearOfBirth = Integer.parseInt(getField(bookData, "birthYearTwo", "0"));

        if (authorFamilyName != null) {
            authors[1] = new Author(authorFamilyName, authorFirstName, nationality, yearOfBirth);
        }

        authorFamilyName = getField(bookData, "familyNameThree", null);
        authorFirstName = getField(bookData, "firstNameThree", null);
        nationality = getField(bookData, "nationalityThree", null);
        yearOfBirth = Integer.parseInt(getField(bookData, "birthYearThree", "0"));

        if (authorFamilyName != null) {
            authors[2] = new Author(authorFamilyName, authorFirstName, nationality, yearOfBirth);
        }

        return authors;
    }

    private void saveBooksToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            // Write CSV header
            writer.write(String.join(",", headers));
            writer.newLine();

            // Write each book to the CSV file
            for (Book book : books) {
                if (book != null) {
                    writer.write(bookToCSVFormat(book));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    private String bookToCSVFormat(Book book) {
        StringBuilder sb = new StringBuilder();
        sb.append(book.getTitle()).append(",");
        sb.append(book.getIsbn()).append(",");
        sb.append(book.isEbook()).append(",");
        sb.append(book.getYearPublished()).append(",");
        sb.append(book.getEdition()).append(",");

        // Append author details
        Author[] authors = book.getAuthors();
        for (Author author : authors) {
            if (author != null) {
                sb.append(author.getFamilyName()).append(",");
                sb.append(author.getFirstName()).append(",");
                sb.append(author.getNationality()).append(",");
                sb.append(author.getYearOfBirth()).append(",");
            } else {
                sb.append(",,"); // Empty fields for missing authors
            }
        }

        return sb.toString();
    }

    // Add a new book
    public void addBook(Book newBook) {
        for (int i = 0; i < MAX_BOOKS; i++) {
            if (books[i] == null) {
                books[i] = newBook;
                saveBooksToCSV(); // Save the new book to the CSV file
                System.out.println("Book added successfully!");
                return;
            }
        }
        System.out.println("Library is full, cannot add more books.");
    }

    // Edit an existing book by title (similar to before)
    public void editBook(String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                Scanner scanner = new Scanner(System.in);

                // Allow editing fields, with the ability to skip
                System.out.print("Enter new title (press Enter to keep \"" + book.getTitle() + "\"): ");
                String newTitle = scanner.nextLine();
                if (!newTitle.trim().isEmpty()) {
                    book.setTitle(newTitle);
                }

                System.out.print("Enter new ISBN (press Enter to keep \"" + book.getIsbn() + "\"): ");
                String newIsbn = scanner.nextLine();
                if (!newIsbn.trim().isEmpty()) {
                    book.setIsbn(newIsbn);
                }

                System.out.print("Is it an eBook (true/false)? (press Enter to keep \"" + book.isEbook() + "\"): ");
                String newIsEbook = scanner.nextLine();
                if (!newIsEbook.trim().isEmpty()) {
                    book.setEbook(Boolean.parseBoolean(newIsEbook));
                }

                System.out.print("Enter year published (press Enter to keep \"" + book.getYearPublished() + "\"): ");
                String newYearPublished = scanner.nextLine();
                if (!newYearPublished.trim().isEmpty()) {
                    book.setYearPublished(Integer.parseInt(newYearPublished));
                }

                saveBooksToCSV(); // Save changes to the CSV file
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Methods to view books, eBooks, non-eBooks, and books by author remain
    // similar.
    public void viewAllBooks() {
        for (Book book : books) {
            if (book != null) {
                System.out.println(book);
            }
        }
    }

    public void viewEbooks() {
        for (Book book : books) {
            if (book != null && book.isEbook()) {
                System.out.println(book);
            }
        }
    }

    public void viewNonEbooks() {
        for (Book book : books) {
            if (book != null && !book.isEbook()) {
                System.out.println(book);
            }
        }
    }

    public void viewBooksByAuthor(String authorName) {
        for (Book book : books) {
            if (book != null) {
                for (Author author : book.getAuthors()) {

                    if (author != null && (author.getFamilyName().equalsIgnoreCase(authorName)
                            || author.getFirstName().equalsIgnoreCase(authorName)
                            || author.getFullName().equalsIgnoreCase(authorName))) {
                        System.out.println(book);
                    }
                }
            }
        }
    }
}
