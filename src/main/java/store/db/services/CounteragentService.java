package store.db.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import store.model.Counteragent;
import store.model.Model;

import java.sql.Statement;

@Component
public class CounteragentService implements Service {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int create(Model counteragent) {
        if (!(counteragent instanceof Counteragent))
            throw new IllegalArgumentException("object isn`t counteragent");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String query = "INSERT INTO counteragents(name) VALUES('" + ((Counteragent) counteragent).getName() + "')";
        jdbcTemplate.update(conn -> conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS),
                keyHolder);
        return (int) keyHolder.getKeys().get("id");
    }

    @Override
    public Model read(Model model) {
        if (!(model instanceof Counteragent))
            throw new IllegalArgumentException("object isn`t counteragent");
        return jdbcTemplate.queryForObject("select * from Counteragent where id=" + ((Counteragent) model).getId(), Counteragent.class);
    }

    @Override
    public void update(Model model) {
        if (!(model instanceof Counteragent))
            throw new IllegalArgumentException("object isn`t counteragent");
        String query = "UPDATE counteragent " +
                "SET name =" + ((Counteragent) model).getName() + " " +
                "Where id =" + ((Counteragent) model).getId();
        jdbcTemplate.execute(query);
    }

    @Override
    public void delete(Model model) {
        if (!(model instanceof Counteragent))
            throw new IllegalArgumentException("object isn`t Counteragent");
        String query = "DELETE from Counteragent where id=" + ((Counteragent) model).getId();
        jdbcTemplate.execute(query);
    }
}
