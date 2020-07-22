package it.iol.ws;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseTestClass {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment environment;

    @Autowired
    @Qualifier("jdbcTemplate1")
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeAll() {
        assertEquals("it", environment.getProperty("spring.profiles.active"), "use -Dspring.profiles.active=it");
        try {
            jdbcTemplate.execute("CREATE TABLE employee (\t\n" +
                    "\tname varchar(25) NOT NULL,\n" +
                    "\trole varchar(25) NOT null,\n" +
                    "\tdepartment varchar(15) null,\n" +
                    "\tCONSTRAINT name_pkey PRIMARY KEY (name)\t\n" +
                    ");");

        } catch (Exception e) {
            assert e.getMessage().contains("already exists") : "error on creating table " + e.getMessage();
        }
    }

}
