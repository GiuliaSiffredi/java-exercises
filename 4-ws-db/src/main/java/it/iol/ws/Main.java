package it.iol.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Main {

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
