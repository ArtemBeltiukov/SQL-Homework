package store.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {

    private static final String DB_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/vjupirtb";
    private static final String LOGIN = "vjupirtb";
    private static final String PASSWORD = "SE0iZbLs8JZfk-WG4ScBFBMZgtLMbd2q";

    public void init() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD)) {
            Statement statement = connection.createStatement();
            String usersQuery = "CREATE TABLE IF NOT EXISTS " +
                    "users(id serial PRIMARY KEY," +
                    "name VARCHAR(45)," +
                    "password VARCHAR(45))";
            String nomenclatureQuery = "CREATE TABLE IF NOT EXISTS " +
                    "nomenclature(id serial PRIMARY KEY, " +
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
                    "bouquets(id serial PRIMARY KEY, " +
                    "orderID INTEGER," +
                    "FOREIGN KEY (orderID) REFERENCES orders (id))";
            String ordersQuery = "CREATE TABLE IF NOT EXISTS " +
                    "orders(id serial PRIMARY KEY," +
                    "userID INTEGER," +
                    "counteragent INTEGER," +
                    "FOREIGN KEY (userID) REFERENCES users (id)," +
                    "FOREIGN KEY (counteragent) REFERENCES counteragents (id))";
            String counteragentsQuery = "CREATE TABLE IF NOT EXISTS " +
                    "counteragents(id serial PRIMARY KEY," +
                    "name VARCHAR(45))";
            String flowersBouquetsQuery = "CREATE TABLE IF NOT EXISTS \n" +
                    "orders_bouquets(id serial PRIMARY KEY," +
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
            statement.executeUpdate(priceQuery);
            statement.executeUpdate(flowersBouquetsQuery);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
