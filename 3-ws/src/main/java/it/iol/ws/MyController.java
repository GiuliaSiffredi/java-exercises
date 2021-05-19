package it.iol.ws;

import it.iol.ws.model.Employee;
import it.iol.ws.model.Greeting;
import it.iol.ws.service.CsvStore;
import it.iol.ws.util.IApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * https://spring.io/guides/gs/rest-service/
 */
@RestController
public class MyController {
    private static final Logger log = LoggerFactory.getLogger(MyController.class);
    private int counter = 0;

    @Autowired
    private IApplicationProperties applicationProperties;

    /**
     * http://localhost:8080/greeting?name=bob' --header 'foo: bar'
     * shows header
     *
     * @return a json with new counter and name
     */
    @GetMapping("/greeting")
    public Greeting greeting(@RequestHeader HttpHeaders headers, @RequestParam(value = "name") String name) {
        log.debug("Greeting name: {}, headers {}", name, headers);
        return new Greeting(counter++, String.format("Hello, %s!", name));
    }

    /**
     * @param newEmployee
     * @param id
     * @return ok if a new employee is saved
     */
    @PostMapping("/employees/{id}")
    ResponseEntity addEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        //log.debug(applicationProperties.getFileOut());
        //log.debug(applicationProperties.getEmailSender());
        log.debug("Store in file: {} employee: {} id: {}", applicationProperties.getFileOut(), newEmployee, id);
        // TODO write in {file.out} id,name,role,date

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = format.format(new Date());
        List<String> a = Arrays.asList(id.toString(), newEmployee.getName(), newEmployee.getRole(), dateString);
        try {
            CsvStore.writeCsv(applicationProperties.getFileOut(), a);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException e) {
            log.error("Errore", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
