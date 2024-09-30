package src;

import java.util.Scanner;

public class LibrarySystem {
    private static LibraryController libraryController = new LibraryController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        /**
         * INFINITY LOOP TO VIEW MENU ITEMS AFTER EACH SELECTED OPERATION HAS BEEN
         * COMPLETED
         * CALLS METHODS FROM CONTROLLER CLASS TO COMPLETE EACH TASK
         */
        while (true) {
            displayMenu();

            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 7) {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.println("WARNING: Invalid choice, please try again." + "\n");
                continue;
            }

            switch (choice) {
                case 1:
                    libraryController.viewAllBooks();
                    break;
                case 2:
                    libraryController.viewEbooks();
                    break;
                case 3:
                    libraryController.viewNonEbooks();
                    break;
                case 4:
                    System.out.print("Enter Author's Name: ");
                    String authorName = scanner.nextLine();
                    libraryController.viewBooksByAuthor(authorName);
                    break;
                case 5:
                    addBook(scanner);
                    break;
                case 6:
                    System.out.print("Enter the title of the book to edit: ");
                    String title = scanner.nextLine();
                    libraryController.editBook(title);
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println("\n--------------------------------\n");
        }
    }

    private static void displayMenu() {
        System.out.println("************************************");
        System.out.println("       Welcome to the Library");
        System.out.println("************************************");
        System.out.println("  1 > View all Books");
        System.out.println("  2 > View eBooks");
        System.out.println("  3 > View non-eBooks");
        System.out.println("  4 > View an Author's Books");
        System.out.println("  5 > Add a Book");
        System.out.println("  6 > Edit a Book");
        System.out.println("  7 > Exit");
        System.out.println("************************************");
        System.out.print("Your choice: ");
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Is it an eBook (true/false)? ");
        boolean isEbook = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter year of publication: ");
        int yearPublished = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Edition No: ");
        int edition = Integer.parseInt(scanner.nextLine());

        /**
         * MAXIMUM OF 3 AUTHORS ALLOWED FOR EACH BOOK ENTRY
         */
        System.out.println("You can add up to 3 authors.");
        Author[] authors = new Author[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter author " + (i + 1) + " family name (or press Enter to skip): ");
            String authorFamilyName = scanner.nextLine();
            if (authorFamilyName.isEmpty())
                break;

            System.out.print("Enter author " + (i + 1) + " first name: ");
            String authorFirstName = scanner.nextLine();

            System.out.print("Enter author " + (i + 1) + " nationality: ");
            String nationality = scanner.nextLine();

            System.out.print("Enter author " + (i + 1) + " year of birth: ");
            int yearOfBirth = Integer.parseInt(scanner.nextLine());

            authors[i] = new Author(authorFamilyName, authorFirstName, nationality, yearOfBirth);
        }

        Book newBook = new Book(title, isbn, isEbook, yearPublished, edition, authors);
        libraryController.addBook(newBook);
    }
}
