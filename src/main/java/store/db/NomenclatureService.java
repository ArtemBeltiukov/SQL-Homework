package store.db;

import store.model.Nomenclature;

import java.sql.*;

public class NomenclatureService {

    private static final String DB_URL = "jdbc:postgresql://hattie.db.elephantsql.com:5432/vjupirtb";
    private static final String DB_URL_TEST = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL";
    private static final String LOGIN = "vjupirtb";
    private static final String PASSWORD = "SE0iZbLs8JZfk-WG4ScBFBMZgtLMbd2q";
    private boolean test;

    public void setTest() {
        this.test = true;
    }

    public int createNomenclature(Nomenclature nomenclature) throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String query = nomenclature.getQuery();
            statement.executeUpdate(query);

            ResultSet rs = statement.executeQuery("Select max(id) as id from nomenclature");
            if (rs.next())
                return rs.getInt("id");
            throw new SQLException();
        }
    }

    private Connection getConnection() throws SQLException {
        if (test)
            return DriverManager.getConnection(DB_URL_TEST);
        else
            return DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
    }
}
