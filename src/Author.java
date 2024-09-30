package src;

public class Author {
    private String familyName;
    private String firstName;
    private String nationality;
    private int yearOfBirth;

    /**
     * CONSTRUCTOR METHOD
     * Initializes with required parameters
     */
    public Author(String familyName, String firstName, String nationality, int yearOfBirth) {
        this.familyName = familyName;
        this.firstName = firstName;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * GETTER AND SETTER METHODS
     */
    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName + " " + familyName;
    }

    public String getNationality() {
        return nationality;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setFamilyName(String name) {
        this.familyName = name;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * RETURNS AUTHOR INFORMATION AS A STRING TO SHOW ALL DATA WITHIN A SINGLE LINE
     */
    @Override
    public String toString() {
        return "Author: " + firstName + ' ' + familyName + ", Nationality: " + nationality + ", Born: " + yearOfBirth;
    }
}
