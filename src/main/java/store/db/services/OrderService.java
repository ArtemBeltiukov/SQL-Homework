package store.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import store.model.Model;
import store.model.Nomenclature;
import store.model.Order;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OrderService implements Service {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String, Object> getAll(int userId){
        String query = "select * from orders where userID=" + userId;

        return jdbcTemplate.call(conn -> conn.prepareCall(query),new ArrayList<>());
    }

    @Override
    @Transactional
    public int create(Model model) {
//        if (!(model instanceof Order))
//            throw new IllegalArgumentException("object isn`t Order");
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        String query;
//        if (((Order) model).getCounteragent() != 0)
//            query = "INSERT INTO orders(userID,counteragent) VALUES (" + ((Order) model).getUserID() + ", " + ((Order) model).getCounteragent() + ")";
//        else
//            query = "INSERT INTO orders(userID,counteragent) VALUES (" + ((Order) model).getUserID() + ", null)";
//
//        jdbcTemplate.update(conn -> conn.prepareStatement(query,
//                Statement.RETURN_GENERATED_KEYS),
//                keyHolder);
//        int id = (int) keyHolder.getKeys().get("id");
//        for (Nomenclature nomenclature : ((Order) model).getNomenclatureList()) {
//            jdbcTemplate.execute("INSERT INTO orders_bouquets(orderID, nomenclature, price, count) " +
//                    "VALUES (" + id + "," + nomenclature.getId() + ", " + nomenclature.getPrice() + ", " + nomenclature.getCount() + ")");
//        }
        return 0;
    }

    @Override
    public Model read(Model model) {
        return jdbcTemplate.queryForObject("select * from orders where id=", Order.class);
    }

    @Override
    @Transactional
    public void update(Model model) {
//        if (!(model instanceof Order))
//            throw new IllegalArgumentException("object isn`t Order");
//        String query = "UPDATE orders " +
//                "SET userID =" + ((Order) model).getUserID() + "," +
//                "counteragent =" + ((Order) model).getCounteragent() +
//                "Where id =" + ((Order) model).getId();
//        jdbcTemplate.execute("Delete from orders_bouquets where orderID=" + ((Order) model).getId());
//        for (Nomenclature nomenclature : ((Order) model).getNomenclatureList()) {
//            jdbcTemplate.execute("INSERT INTO orders_bouquets(orderID, nomenclature, price, count) " +
//                    "VALUES (" + ((Order) model).getId() + "," + nomenclature + ", " + nomenclature.getPrice() + ", " + nomenclature.getCount() + ")");
//        }
//        jdbcTemplate.execute(query);
    }

    @Override
    @Transactional
    public void delete(Model model) {
        if (!(model instanceof Order))
            throw new IllegalArgumentException("object isn`t Order");
        String query = "DELETE from Nomenclature where id=" + ((Order) model).getId();
        jdbcTemplate.execute("Delete from orders_bouquets where orderID=" + ((Order) model).getId());
        jdbcTemplate.execute(query);
    }
}
