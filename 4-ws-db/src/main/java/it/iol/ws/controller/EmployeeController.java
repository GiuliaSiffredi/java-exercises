package it.iol.ws.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.control.Either;
import io.vavr.control.Option;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.model.ErrorBean;
import it.iol.ws.service.EmployeeService;
import it.iol.ws.util.JsonHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import static io.vavr.API.*;
import static io.vavr.Patterns.*;

/**
 * https://spring.io/guides/gs/rest-service/
 */
@Slf4j
@RestController
@RequestMapping("/employees/v1")
public class EmployeeController {

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate;

    /**
     * @param employee
     * @param id
     * @return
     */
    @PostMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<JsonNode> addEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        log.debug(String.format("received %s id: %d", employee, id));
        Either<ErrorBean, Report> json = EmployeeService.insert(jdbcTemplate, employee);
        return Match(json).of(
                Case($Right($()), report -> new ResponseEntity<>(JsonHelper.javaToJson(report), HttpStatus.OK)),
                Case($Left($()), errors -> new ResponseEntity<>(JsonHelper.javaToJson(errors), HttpStatus.BAD_REQUEST)));
    }

    @GetMapping(path = "/{name}", produces = "application/json")
    ResponseEntity<JsonNode> getEmployee(@PathVariable String name) {
        log.debug(String.format("received id: %s", name));
        Option<Employee> json = EmployeeService.get(jdbcTemplate, name);
        return Match(json).of(
                Case($Some($()), e -> new ResponseEntity<>(JsonHelper.javaToJson(e), HttpStatus.OK)),
                Case($None(), () -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND)));
    }
}