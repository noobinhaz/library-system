package src;

public class Book {
    private String title;
    private String isbn;
    private boolean isEbook;
    private int yearPublished;
    private int edition;
    private Author[] authors;

    /**
     * CONSTRUCTOR METHOD
     * Initializes with required parameters
     */
    public Book(String title, String isbn, boolean isEbook, int yearPublished, int edition, Author[] authors) {
        this.title = title;
        this.isbn = isbn;
        this.isEbook = isEbook;
        this.yearPublished = yearPublished;
        this.edition = edition;
        this.authors = authors;
    }

    /**
     * GETTER AND SETTER METHODS
     */
    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isEbook() {
        return isEbook;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public int getEdition() {
        return edition;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEbook(boolean isEbook) {
        this.isEbook = isEbook;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    /**
     * RETURNS BOOK INFORMATION ALONG WITH AUTHORS AS A STRING TO SHOW ALL DATA AS A
     * COMPLETE FORMATTED STRING
     */
    @Override
    public String toString() {
        StringBuilder authorDetails = new StringBuilder();
        for (Author author : authors) {
            if (author != null) {
                authorDetails.append(author.toString()).append("\n");
            }
        }
        return "Title: " + title + "\nISBN: " + isbn + "\nYear Published: " + yearPublished +
                "\neBook: " + (isEbook ? "TRUE" : "FALSE") + "\nEdition: " + edition + "\n" + authorDetails;
    }
}
