package store.db.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import store.model.Model;
import store.model.Nomenclature;
import store.model.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "enable", name = "jdbc")
public class NomenclatureService implements Service {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int create(Model nomenclature) {
        if (!(nomenclature instanceof Nomenclature))
            throw new IllegalArgumentException("object isn`t Nomenclature");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO nomenclature(name, bouquetId, balance, price) VALUES('" + ((Nomenclature) nomenclature).getName() + "', "+((Nomenclature) nomenclature).getBouquetId()+", 1, 1)";
        jdbcTemplate.update(conn -> conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS),
                keyHolder);
        int id = ((BigInteger) keyHolder.getKeys().get("GENERATED_KEY")).intValue();
        jdbcTemplate.execute("INSERT INTO balance VALUES(" + id + "," + ((Nomenclature) nomenclature).getBalance().getAmount() + ")");
        jdbcTemplate.execute("INSERT INTO price VALUES(" + id + "," + ((Nomenclature) nomenclature).getPrice().getPrice() + ")");
        return id;
    }

    @Override
    @Cacheable("nomenclature")
    public Model read(Model model) {
        if (!(model instanceof Nomenclature))
            throw new IllegalArgumentException("object isn`t Nomenclature");
        return jdbcTemplate.queryForObject("select id, name, price, amount " +
                "from nomenclature " +
                "left join price" +
                "on nomenclature.id = price.nomenclature" +
                "left join balance" +
                "on nomenclature.id = balance.nomenclature" +
                "where id=" + ((Nomenclature) model).getId(), Nomenclature.class);
    }

    @Override
    @Transactional
    public void update(Model nomenclature) {
        if (!(nomenclature instanceof Nomenclature))
            throw new IllegalArgumentException("object isn`t Nomenclature");
        jdbcTemplate.execute("UPDATE price " +
                "set price = " + ((Nomenclature) nomenclature).getPrice() +
                "where nomenclature = " + ((Nomenclature) nomenclature).getId());
        jdbcTemplate.execute("UPDATE counteragent " +
                "SET name =" + ((Nomenclature) nomenclature).getName() + " " +
                "Where id =" + ((Nomenclature) nomenclature).getId());
    }

    @Override
    @Transactional
    public void delete(Model nomenclature) {
        if (!(nomenclature instanceof Nomenclature))
            throw new IllegalArgumentException("object isn`t Nomenclature");
        jdbcTemplate.execute("DELETE from Nomenclature where id=" + ((Nomenclature) nomenclature).getId());
        jdbcTemplate.execute("DELETE from Balance where nomenclature=" + ((Nomenclature) nomenclature).getId());
        jdbcTemplate.execute("DELETE from Price where nomenclature=" + ((Nomenclature) nomenclature).getId());
    }

    @Override
    public List<Model> getAll() {
        return (List<Model>) jdbcTemplate.queryForObject("select * from nomenclature", Nomenclature.class);
    }

    @Override
    public List<Nomenclature> getAllByCriteria() {
        return null;
    }

}
