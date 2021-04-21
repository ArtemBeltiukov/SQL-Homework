package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Bouquet;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class BouquetServiceTest {

    private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static Connection connection;

    @BeforeAll
    public static void init() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
        DBInitializer dbInitializer = new DBInitializer();
        dbInitializer.testInit();
    }

    @AfterAll
    public static void destroy() throws SQLException {
        connection.close();
    }

    @Test
    void createBouquet() throws SQLException {
        int expected = 1;
        Statement statement = connection.createStatement();
        BouquetService bouquetService = new BouquetService();
        bouquetService.setTest();
        Bouquet bouquet = new Bouquet(1, 100, 100);
        statement.executeUpdate("INSERT INTO orders VALUES(" + expected + ",null,null)");
        bouquetService.createBouquet(bouquet);
        ResultSet rs = statement.executeQuery("Select * from bouquets");
        if (rs.next()) {
            int actual = rs.getInt("orderID");
            assertEquals(expected, actual);
        } else
            fail();
    }
}