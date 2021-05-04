package store.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import store.model.Model;
import store.model.Nomenclature;
import store.model.Order;
import store.model.User;

import java.sql.Statement;

@Component
public class RealizationService implements Service {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int create(Model model) {
//        if (!(model instanceof Order))
//            throw new IllegalArgumentException("object isn`t Order");
//        if (((Order) model).isRealized()) {
//            throw new IllegalArgumentException("Order already was realized. Crate another order and realize it.");
//        }
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(conn -> conn.prepareStatement("INSERT INTO realizationOfGoods(orderID) " +
//                        "VALUES(" + ((Order) model).getId() + ")",
//                Statement.RETURN_GENERATED_KEYS),
//                keyHolder);
//        int id = (int) keyHolder.getKeys().get("id");
//        ((Order) model).setRealized(true);
        return 1;
    }

    @Override
    public Model read(Model model) {
        if (!(model instanceof Order))
            throw new IllegalArgumentException("object isn`t Nomenclature");
        return jdbcTemplate.queryForObject("select * " +
                        "from orders where id=" + ((Order) model).getId() +
                        "inner join realizationOfGoods" +
                        "on orders.id = realizationOfGoods.orderID"

                , Order.class);
    }

    @Override
    public void update(Model model) {

    }

    @Override
    public void delete(Model model) {

    }
}
