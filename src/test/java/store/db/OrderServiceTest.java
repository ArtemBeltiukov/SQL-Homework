package store.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.model.Bouquet;
import store.model.Nomenclature;
import store.model.Order;

import java.sql.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
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
    void createOrder() throws SQLException {
        Statement statement = connection.createStatement();
        OrderService orderService = new OrderService();
        orderService.setTest();
        statement.executeUpdate("INSERT INTO users(name,password) VALUES('Artem',123)");
        statement.executeUpdate("INSERT INTO counteragents VALUES(1,'OOO My Defense')");
        Order order = new Order(1, 1);
        orderService.createOrder(order);
        ResultSet resultSet = statement.executeQuery("select * from orders");
        assertTrue(resultSet.next());
    }

    @Test
    void realizeOrder() throws SQLException {
        int count = 10;
        int amount = 15;
        int expected = 5;
        Statement statement = connection.createStatement();
        OrderService orderService = new OrderService();
        BalanceService balanceService = new BalanceService();
        BouquetService bouquetService = new BouquetService();
        NomenclatureService nomenclatureService = new NomenclatureService();
        OrderBouquetsService orderBouquetsService = new OrderBouquetsService();
        statement.executeUpdate("INSERT INTO users VALUES(1,'Artem',123)");
        statement.executeUpdate("INSERT INTO counteragents VALUES(1,'OOO My Defense')");
        Order order = new Order(1, 1);
        orderService.setTest();
        int orderID = orderService.createOrder(order);
        orderBouquetsService.setTest();
        balanceService.setTest();
        bouquetService.setTest();
        nomenclatureService.setTest();
        Bouquet bouquet = new Bouquet(orderID, amount, 10);
        bouquet.setId(1);
        bouquet.setCount(count);
        int bouquetID = bouquetService.createBouquet(bouquet);
        Nomenclature nomenclature = new Nomenclature(bouquetID, "rose", 10, amount, new HashMap<String, String>());
        nomenclature.setId(bouquetID);
        nomenclatureService.createNomenclature(nomenclature);
        balanceService.createBalance(nomenclature);
        orderBouquetsService.createOrderBouquets(bouquet);
        order.setId(orderID);
        orderService.realizeOrder(order);
        ResultSet resultSet = statement.executeQuery("select amount from balance");
        if (resultSet.next())
            assertEquals(expected, resultSet.getInt("amount"));
        else
            fail();
    }
}