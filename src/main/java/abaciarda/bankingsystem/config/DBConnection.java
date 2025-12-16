package abaciarda.bankingsystem.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static final Properties properties = new Properties();

    static {
        try(InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is == null) {
                throw new RuntimeException("db.properties bulunamadı");
            }

            properties.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Config dosyası okunamadı", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
        } catch (Exception e) {
            throw new RuntimeException("DB bağlantısı kurulamadı", e);
        }
    }
}
