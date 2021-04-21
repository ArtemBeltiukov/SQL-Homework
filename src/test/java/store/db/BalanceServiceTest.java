package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Nomenclature;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BalanceServiceTest {
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
    void createBalance() throws SQLException {
        int expected = 10;
        BalanceService balanceService = new BalanceService();
        balanceService.setTest();
        Nomenclature nomenclature = new Nomenclature(1, "rose", 100, expected, new HashMap<String, String>());
        nomenclature.setId(1);
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO nomenclature(bouquetID, name) VALUES(null,'rose')");
        balanceService.createBalance(nomenclature);
        ResultSet rs = statement.executeQuery("Select amount from balance where nomenclature=" + 1);
        if (rs.next()){
            int actual = rs.getInt("amount");
            assertEquals(expected, actual);
        }else
            fail();
    }
}