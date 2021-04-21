package store.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInitializer {

    private static final String DB_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/vjupirtb";
    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static final String LOGIN = "vjupirtb";
    private static final String PASSWORD = "SE0iZbLs8JZfk-WG4ScBFBMZgtLMbd2q";

    public void testInit() {
        try (Connection connection = DriverManager.getConnection(DB_URL_TEST)) {
            Statement statement = connection.createStatement();
            String usersQuery = "CREATE TABLE IF NOT EXISTS " +
                    "users(id INTEGER IDENTITY PRIMARY KEY," +
                    "name VARCHAR(45)," +
                    "password VARCHAR(45))";
            String nomenclatureQuery = "CREATE TABLE IF NOT EXISTS " +
                    "nomenclature(id INTEGER IDENTITY PRIMARY KEY, " +
                    "name VARCHAR(45)," +
                    "bouquetID INTEGER, " +
                    "FOREIGN KEY (bouquetID) REFERENCES bouquets (id))";
            String specificationsQuery = "CREATE TABLE IF NOT EXISTS " +
                    "specifications(nomenclature INTEGER, " +
                    "specification_name VARCHAR(45)," +
                    "specification_value VARCHAR(45)," +
                    "FOREIGN KEY (nomenclature) REFERENCES nomenclature (id))";
            String priceQuery = "CREATE TABLE IF NOT EXISTS " +
                    "price(nomenclature INTEGER," +
                    "price INTEGER," +
                    "FOREIGN KEY (nomenclature) REFERENCES nomenclature (id))";
            String balanceQuery = "CREATE TABLE IF NOT EXISTS " +
                    "balance(nomenclature INTEGER," +
                    "amount INTEGER," +
                    "FOREIGN KEY (nomenclature) REFERENCES nomenclature (id))";
            String bouquetsQuery = "CREATE TABLE IF NOT EXISTS " +
                    "bouquets(id INTEGER IDENTITY PRIMARY KEY, " +
                    "orderID INTEGER," +
                    "FOREIGN KEY (orderID) REFERENCES orders (id))";
            String ordersQuery = "CREATE TABLE IF NOT EXISTS " +
                    "orders(id INTEGER IDENTITY PRIMARY KEY," +
                    "userID INTEGER," +
                    "counteragent INTEGER," +
                    "FOREIGN KEY (userID) REFERENCES users (id)," +
                    "FOREIGN KEY (counteragent) REFERENCES counteragents (id))";
            String counteragentsQuery = "CREATE TABLE IF NOT EXISTS " +
                    "counteragents(id INTEGER IDENTITY PRIMARY KEY," +
                    "name VARCHAR(45))";
            String flowersBouquetsQuery = "CREATE TABLE IF NOT EXISTS \n" +
                    "orders_bouquets(id INTEGER AUTO_INCREMENT PRIMARY KEY," +
                    "orderID INTEGER," +
                    "bouquet INTEGER," +
                    "price INTEGER," +
                    "count INTEGER," +
                    "FOREIGN KEY (orderID) REFERENCES orders (id))";
            statement.executeUpdate(usersQuery);
            statement.executeUpdate(counteragentsQuery);
            statement.executeUpdate(ordersQuery);
            statement.executeUpdate(bouquetsQuery);
            statement.executeUpdate(nomenclatureQuery);
            statement.executeUpdate(specificationsQuery);
            statement.executeUpdate(balanceQuery);
            statement.executeUpdate(flowersBouquetsQuery);
            statement.executeUpdate(priceQuery);
            String userDataQuery = "INSERT INTO users VALUES()";

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
