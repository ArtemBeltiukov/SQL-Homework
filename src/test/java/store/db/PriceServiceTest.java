package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Nomenclature;

import java.sql.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceTest {
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
    void createPrice() throws SQLException {
        int expected = 10;
        Statement statement = connection.createStatement();
        PriceService priceService = new PriceService();
        priceService.setTest();
        Nomenclature nomenclature = new Nomenclature(1, "rose", expected, 10, new HashMap<>());
        nomenclature.setId(1);
        statement.executeUpdate("INSERT INTO nomenclature(name,bouquetID) VALUES('rose',null)");
        priceService.createPrice(nomenclature);
        ResultSet resultSet = statement.executeQuery("select * from price");
        if (resultSet.next()) {
            assertEquals(expected, resultSet.getInt("price"));
        } else
            fail();
    }
}