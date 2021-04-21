package store.db;

import store.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    private static final String DB_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/vjupirtb";
    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static final String LOGIN = "vjupirtb";
    private static final String PASSWORD = "SE0iZbLs8JZfk-WG4ScBFBMZgtLMbd2q";
    private boolean test;

    public void setTest() {
        this.test = true;
    }

    private Connection getConnection() throws SQLException {
        if (test)
            return DriverManager.getConnection(DB_URL_TEST);
        else
            return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }

    public int createUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = user.getQuery();
            return statement.executeUpdate(query);
        }
    }
}
