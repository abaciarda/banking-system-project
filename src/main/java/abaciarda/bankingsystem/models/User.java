package abaciarda.bankingsystem.models;

public class User {
    private int id;
    private final String name;
    private final String surname;
    private final String ssn;
    private final String hashedPassword;
    private final String iban;

    public User(String name, String surname, String ssn, String hashedPassword, String iban) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.hashedPassword = hashedPassword;
        this.iban = iban;
    }

    public User(int id, String name, String surname, String ssn, String hashedPassword, String iban) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.hashedPassword = hashedPassword;
        this.iban = iban;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getSsn() {
        return ssn;
    }

    public String getIban() { return iban; }
}
