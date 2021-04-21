package store.db;

import store.model.Order;

import java.sql.*;

public class OrderService {
    private static final String DB_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/vjupirtb";
    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static final String LOGIN = "vjupirtb";
    private static final String PASSWORD = "SE0iZbLs8JZfk-WG4ScBFBMZgtLMbd2q";
    private boolean test;

    public void setTest() {
        this.test = true;
    }

    private Connection getConnection() throws SQLException {
        if (test)
            return DriverManager.getConnection(DB_URL_TEST);
        else
            return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }

    public int createOrder(Order order) throws SQLException {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query;
            if (test)
                query = "INSERT INTO orders(userID,counteragent) VALUES (" + order.getUserID() + ", " + order.getCounteragent() + ")";
            else
                query = order.getQuery();
            statement.executeUpdate(query);

            ResultSet rs = statement.executeQuery("Select max(id) as id from orders");
            if (rs.next())
                return rs.getInt("id");
            throw new SQLException();
        }
    }

    public void realizeOrder(Order order) throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             Statement statement1 = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select orders_bouquets.id , orders_bouquets.count, nomenclature.id as \"nomenID\"\n" +
                    "from orders_bouquets inner join nomenclature \n" +
                    "on orders_bouquets.bouquet=nomenclature.bouquetID \n" +
                    "where orderID=" + order.getId());
            while (rs.next()) {
                int amount = rs.getInt("count");
                int nomenID = rs.getInt("nomenID");
                ResultSet rs1 = statement1.executeQuery("SELECT amount from balance where nomenclature=" + nomenID);
                if (rs1.next()) {
                    amount = rs1.getInt("amount") - amount;
                    statement1.executeUpdate("UPDATE balance SET amount=" + amount + " WHERE nomenclature=" + nomenID);
                } else {
                    throw new SQLException();
                }
            }
        }
    }
}
