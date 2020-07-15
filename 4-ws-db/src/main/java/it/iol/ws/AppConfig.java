package it.iol.ws;

import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "mydatasource1.datasource")
    public DataSource dataSource1() {
        val ds= DataSourceBuilder.create().build();
        assert (ds != null);
        return ds;
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("db1") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
