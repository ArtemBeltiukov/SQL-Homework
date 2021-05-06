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
        final String driverClassName = "org.h2.Driver";
        final String jdbcUrl = "jdbc:h2:mem:test";
//        final String username = "vjupirtb";
//        final String password = "yKcpQTJcIWRywgWbHgR023nGHMXnNySq";
        final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).url(jdbcUrl).build();
        return new JdbcTemplate(dataSource);
    }
}
