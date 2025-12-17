package abaciarda.bankingsystem.services;

import abaciarda.bankingsystem.dao.UserDao;
import abaciarda.bankingsystem.models.User;
import abaciarda.bankingsystem.utils.GenerateIban;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registerUser(String name, String surname, String password, String ssn) {

        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be empty.");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (ssn == null || !ssn.matches("\\d{11}")) {
            throw new IllegalArgumentException("SSN must be 11 digits.");
        }

        if (userDao.findBySSN(ssn) != null) {
            throw new IllegalStateException("This SSN number has already been taken.");
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String iban = GenerateIban.generateIban(ssn);

        User user = new User(name, surname, ssn, hashedPassword, iban);
        userDao.saveUser(user);
    }
}
