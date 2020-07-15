package it.iol.ws;

import java.util.concurrent.atomic.AtomicLong;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * https://spring.io/guides/gs/rest-service/
 */
@RestController
public class MyController {
    private static final Logger log = LoggerFactory.getLogger(MyController.class);
    private final AtomicLong counter = new AtomicLong();

    /**
     *
     * @param name
     * @return
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello, %s!", name));
    }

    /**
     * @param newEmployee
     * @param id
     * @return
     */
    @PostMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        log.debug("store in db " + newEmployee + " id: " + id);

        return ResponseEntity.ok().build();
    }

}
