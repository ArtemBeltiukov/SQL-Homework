package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Counteragent;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class CounteragentServiceTest {

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
    void createCounteragent() throws SQLException {
        String expected = "OOO \"test\"";
        Statement statement = connection.createStatement();
        CounteragentService counteragentService = new CounteragentService();
        Counteragent counteragent = new Counteragent(expected);
        counteragent.setId(1);
        counteragentService.setTest();
        counteragentService.createCounteragent(counteragent);
        ResultSet rs = statement.executeQuery("Select * from counteragents");
        if (rs.next()) {
            String actual = rs.getString("name");
            assertEquals(expected, actual);
        } else
            fail();
    }
}