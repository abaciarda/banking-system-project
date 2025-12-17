package abaciarda.bankingsystem.dao;

import abaciarda.bankingsystem.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO users (name, surname, ssn, hashed_password, iban) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getSsn());
            stmt.setString(4, user.getHashedPassword());
            stmt.setString(5, user.getIban());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("User couldnt saved.", e);
        }
    }

    public User findBySSN(String ssn) {
        String sql = "SELECT * FROM users WHERE ssn = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ssn);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                return new User(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("surname"),
                    result.getString("ssn"),
                    result.getString("hashed_password"),
                    result.getString("iban")
                );
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Customer couldnt find.", e);
        }
    }
}
