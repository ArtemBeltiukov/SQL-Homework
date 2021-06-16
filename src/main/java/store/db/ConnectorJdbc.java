package store.db;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Controller
public class ConnectorJdbc implements Connector {
    @Bean
    public JdbcTemplate jdbcTemplate() {
        final String driverClassName = "com.mysql.cj.jdbc.Driver";
        final String jdbcUrl = "jdbc:mysql://localhost:3306/flower_store";//"jdbc:h2:mem:test";
        final String username = "user";
        final String password = "123";
        final DataSource dataSource = DataSourceBuilder.create().driverClassName(driverClassName).username(username).password(password).url(jdbcUrl).build();
        return new JdbcTemplate(dataSource);
    }
}
