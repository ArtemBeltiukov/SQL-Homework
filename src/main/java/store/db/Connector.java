package store.db;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Controller
public class Connector {
    @Bean
    public JdbcTemplate jdbcTemplate() {
        final String driverClassName = "org.postgresql.Driver";
        final String jdbcUrl = "jdbc:postgresql://hattie.db.elephantsql.com:5432/vjupirtb";
        final String username = "vjupirtb";
        final String password = "SE0iZbLs8JZfk-WG4ScBFBMZgtLMbd2q";
        final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).username(username).password(password).build();
        return new JdbcTemplate(dataSource);
    }
}
