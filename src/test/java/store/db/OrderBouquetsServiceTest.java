package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Bouquet;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderBouquetsServiceTest {

    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static Connection connection;

    @BeforeAll
    public static void init() throws SQLException {
        connection = DriverManager.getConnection(DB_URL_TEST);
        DBInitializer dbInitializer = new DBInitializer();
        dbInitializer.testInit();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO orders VALUES(1,null,null)");
    }

    @AfterAll
    public static void destroy() throws SQLException {
        connection.close();
    }

    @Test
    void createOrderBouquets() throws SQLException {
        int expected = 10;
        Statement statement = connection.createStatement();
        OrderBouquetsService orderBouquetsService = new OrderBouquetsService();
        orderBouquetsService.setTest();
        Bouquet bouquet = new Bouquet(1, 10, 100);
        bouquet.setCount(expected);
        orderBouquetsService.createOrderBouquets(bouquet);
        ResultSet resultSet = statement.executeQuery("Select * from orders_bouquets");
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            assertEquals(expected, count);
        } else
            fail();
    }

    @Test
    void createOrderBouquetsTestPrice() throws SQLException {
        int expected = 100;
        Statement statement = connection.createStatement();
        OrderBouquetsService orderBouquetsService = new OrderBouquetsService();
        orderBouquetsService.setTest();
        Bouquet bouquet = new Bouquet(1, 10, expected);
        orderBouquetsService.createOrderBouquets(bouquet);
        ResultSet resultSet = statement.executeQuery("Select * from orders_bouquets");
        if (resultSet.next()) {
            int price = resultSet.getInt("price");
            assertEquals(expected, price);
        } else
            fail();
    }
}