package it.iol.ws.controller;

import com.fasterxml.jackson.databind.JsonNode;
import it.iol.ws.validator.ValidationException;
import it.iol.ws.model.Employee;
import it.iol.ws.service.EmployeeService;
import it.iol.ws.util.JsonHelper;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * https://spring.io/guides/gs/rest-service/
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate;

    /**
     * @param employee
     * @param id
     * @return
     */
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<JsonNode> addEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        log.debug("received %s id: %d {}", employee, id);
        try {
            val report = EmployeeService.insert(jdbcTemplate, employee);
            return new ResponseEntity<>(JsonHelper.objectToJson(report), HttpStatus.OK);
        } catch (ValidationException errors) {
            return new ResponseEntity<>(JsonHelper.objectToJson(errors.getErrors()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/{name}", produces = "application/json")
    ResponseEntity<JsonNode> getEmployee(@PathVariable String name) {
        log.debug("received id: {}", name);
        val json = EmployeeService.getById(jdbcTemplate, name);
        if (json.isPresent()) {
            val e = json.get();
            return new ResponseEntity<>(JsonHelper.objectToJson(e), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(path = "role/{role}", produces = "application/json")
    ResponseEntity<JsonNode> getEmployeeByRole(@PathVariable String role) {
        log.debug("received id: {}", role);
        val list = EmployeeService.getByRole(jdbcTemplate, role);
        return new ResponseEntity<>(JsonHelper.objectToJson(list), HttpStatus.OK);
    }
}