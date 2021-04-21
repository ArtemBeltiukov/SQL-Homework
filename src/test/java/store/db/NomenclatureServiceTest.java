package store.db;

import org.junit.jupiter.api.*;
import store.model.Nomenclature;

import java.sql.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NomenclatureServiceTest {

    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static Connection connection;

    @BeforeAll
    public static void init() throws SQLException {
        connection = DriverManager.getConnection(DB_URL_TEST);
        DBInitializer dbInitializer = new DBInitializer();
        dbInitializer.testInit();
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO bouquets VALUES(1,null)");
    }

    @AfterAll
    public static void destroy() throws SQLException {
        connection.close();
    }

    @Test
    void createNomenclature() throws SQLException {
        String actual = "rose";
        Statement statement = connection.createStatement();
        NomenclatureService nomenclatureService = new NomenclatureService();
        nomenclatureService.setTest();
        Nomenclature nomenclature = new Nomenclature(1, actual, 100, 100, new HashMap<String, String>());
        nomenclatureService.createNomenclature(nomenclature);
        ResultSet resultSet = statement.executeQuery("select * from nomenclature");
        if (resultSet.next()) {
            String expected = resultSet.getString("name");
            assertEquals(expected, actual);
        } else
            fail();
    }

    @Test
    void createNomenclatureTestID() throws SQLException {
        int actual = 1;
        Statement statement = connection.createStatement();
        NomenclatureService nomenclatureService = new NomenclatureService();
        nomenclatureService.setTest();
        Nomenclature nomenclature = new Nomenclature(actual, "rose", 100, 100, new HashMap<String, String>());
        nomenclatureService.createNomenclature(nomenclature);
        ResultSet resultSet = statement.executeQuery("select * from nomenclature");
        if (resultSet.next()) {
            int expected = resultSet.getInt("bouquetID");
            assertEquals(expected, actual);
        } else
            fail();
    }
}