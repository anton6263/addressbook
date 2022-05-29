package model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String homepage;

    public ContactData (String firstname, String lastname, String email, String homepage) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.homepage = homepage;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getHomepage() {
        return homepage;
    }
}