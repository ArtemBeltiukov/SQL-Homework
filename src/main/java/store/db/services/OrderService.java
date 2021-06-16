package store.db.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import store.model.Model;
import store.model.Nomenclature;
import store.model.Order;
import store.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
public class OrderService implements Service {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Cacheable("orders")
    public Map<String, Object> getAll(BigInteger userId) {
        String query = "select * from orders where userID=" + userId;
        log.info("query to database");
        return jdbcTemplate.call(conn -> conn.prepareCall(query), new ArrayList<>());
    }

    @Override
    @Transactional
    public int create(Model model) {
        if (!(model instanceof Order))
            throw new IllegalArgumentException("object isn`t Order");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query;
        if (((Order) model).getCounteragent() != 0)
            query = "INSERT INTO orders(userID,counteragent) VALUES (" + ((Order) model).getUserID() + ", " + ((Order) model).getCounteragent() + ")";
        else
            query = "INSERT INTO orders(userID,counteragent) VALUES (" + ((Order) model).getUserID() + ", null)";

        jdbcTemplate.update(conn -> conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS),
                keyHolder);
        int id = ((BigInteger) keyHolder.getKeys().get("GENERATED_KEY")).intValue();
        return id;
    }

    @Override
    public Model read(Model model) {
        return jdbcTemplate.queryForObject("select * from orders where id=", Order.class);
    }

    @Override
    @Transactional
    public void update(Model model) {

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

    @Override
    public List<Model> getAll() {
        return null;
    }

    @Override
    public List<Nomenclature> getAllByCriteria() {
        return null;
    }

}
