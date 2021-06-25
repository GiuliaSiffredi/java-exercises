package it.iol.ws;

import it.iol.ws.util.Utils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;





@Slf4j
@Configuration

public class AppConfig {

    /*@Bean(name = "db1")
    @ConfigurationProperties(prefix = "mydatasource1.datasource")
    public DataSource dataSource1() {
        DataSource ds = DataSourceBuilder.create().build();
        assert (ds != null);
        return ds;
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("db1") DataSource ds) {
        return new JdbcTemplate(ds);
    }



    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "mydatasource2.datasource")
    public DataSource dataSource2() {
        DataSource ds = DataSourceBuilder.create().build();
        assert (ds != null);
        return ds;
    }

    @Bean(name = "jdbcTemplate2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("db2") DataSource ds) {
        return new JdbcTemplate(ds);
    }*/

    static void createDB(JdbcTemplate jdbcTemplate) {
        log.info("create table employee");
        try {
            jdbcTemplate.execute("CREATE TABLE employee (\n" +
                    "id uuid NOT NULL,\n" +
                    "name varchar(25) NOT NULL,\n" +
                    "role varchar(25) NOT null,\n" +
                    "department varchar(15) null,\n" +
                    "CONSTRAINT id_pkey PRIMARY KEY (id)\n" +
                    ");");
        } catch (Exception e) {
            assert e.getMessage().contains("already exists") : "error on creating table " + e.getMessage();
        }
    }

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "mydatasource1.datasource")
    public DataSource dataSource1() {
        val ds = DataSourceBuilder.create().build();
        assert (ds != null);
        return ds;
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("db1") DataSource ds) {
        val jdbcTemplate = new JdbcTemplate(ds);
        boolean env = Utils.getEnv().equals("embeddedh2");
        if (env) createDB(jdbcTemplate);
        return jdbcTemplate;
    }

}
