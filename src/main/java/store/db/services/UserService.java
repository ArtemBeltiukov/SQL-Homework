package store.db.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import store.model.Model;
import store.model.Nomenclature;
import store.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.Statement;
import java.util.List;

//@Component
public class UserService implements Service {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int create(Model model) {
        if (!(model instanceof User))
            throw new IllegalArgumentException("object isn`t User");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO users(name,password,role) VALUES('" + ((User) model).getName() + "','" + ((User) model).getPassword() + "','" + ((User) model).getRole() + "')";
        jdbcTemplate.update(conn -> conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS),
                keyHolder);
        return ((BigInteger) keyHolder.getKeys().get("GENERATED_KEY")).intValue();
    }

    @Override
    public Model read(Model model) {
        if (!(model instanceof User))
            throw new IllegalArgumentException("object isn`t User");
        return jdbcTemplate.queryForObject("select * " +
                "from user " +
                "where id=" + ((User) model).getId() +
                "or (name = '" + ((User) model).getName() + "; and password=;" + ((User) model).getPassword() + ";)", User.class);
    }

    @Override
    public void update(Model model) {

    }

    @Override
    public void delete(Model model) {

    }

    @Override
    public List<Model> getAll() {
        return null;
    }

    @Override
    public List<Nomenclature> getAllByCriteria() {
        return null;
    }

    public User getByName(String name) {
        return jdbcTemplate.queryForObject("select * from users where name = '" + name + "' LIMIT 1", User.class);
    }
}
