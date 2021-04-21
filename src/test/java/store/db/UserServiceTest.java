package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Nomenclature;
import store.model.User;

import java.sql.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static Connection connection;

    @BeforeAll
    public static void init() throws SQLException {
        connection = DriverManager.getConnection(DB_URL_TEST);
        DBInitializer dbInitializer = new DBInitializer();
        dbInitializer.testInit();
    }

    @AfterAll
    public static void destroy() throws SQLException {
        connection.close();
    }

    @Test
    void createUser() throws SQLException {
        Statement statement = connection.createStatement();
        UserService userService = new UserService();
        userService.setTest();
        User user = new User("Artem", "123");
        userService.createUser(user);
        ResultSet resultSet = statement.executeQuery("select * from users");
        if (resultSet.next()) {
            User actual = new User(resultSet.getString("name"), resultSet.getString("password"));
            assertEquals(user, actual);
        } else
            fail();
    }
}