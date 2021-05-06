package store.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class DBInitializer {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String usersQuery = "CREATE TABLE IF NOT EXISTS " +
                "users(id serial PRIMARY KEY," +
                "name VARCHAR(45)," +
                "password VARCHAR(45)," +
                "role VARCHAR(45))";
        String realizationOfGoodsQuery = "CREATE TABLE IF NOT EXISTS " +
                "realizationOfGoods(id serial PRIMARY KEY," +
                "orderID int," +
                "FOREIGN KEY (orderID) REFERENCES orders (id))";
        String nomenclatureQuery = "CREATE TABLE IF NOT EXISTS " +
                "nomenclature(id serial PRIMARY KEY, " +
                "name VARCHAR(45))";
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
                "nomenclature INTEGER," +
                "price INTEGER," +
                "count INTEGER," +
                "FOREIGN KEY (orderID) REFERENCES orders (id))";
        jdbcTemplate.execute(usersQuery);
        jdbcTemplate.execute(counteragentsQuery);
        jdbcTemplate.execute(ordersQuery);
        jdbcTemplate.execute(nomenclatureQuery);
        jdbcTemplate.execute(specificationsQuery);
        jdbcTemplate.execute(balanceQuery);
        jdbcTemplate.execute(priceQuery);
        jdbcTemplate.execute(flowersBouquetsQuery);
        jdbcTemplate.execute(realizationOfGoodsQuery);
    }

}
