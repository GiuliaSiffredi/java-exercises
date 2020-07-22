package it.iol.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String env = "spring.profiles.active";
        String prop = System.getenv(env) != null ? System.getenv(env) : System.getProperty(env);
        if (prop == null || (!prop.equals("local") && !prop.equals("dev") && !prop.equals("it") && !prop.equals("prod") && !prop.equals("test"))) {
            log.error("bad " + env + " ******************* use -Dspring.profiles.active={local|it|dev|prod|test} *******************");
            assert (false);
            System.exit(1);
        }
        SpringApplication.run(Main.class, args);
    }

}
