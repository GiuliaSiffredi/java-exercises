package it.iol.ws.service;

import it.iol.ws.validator.ValidationException;
import it.iol.ws.sql.DaoEmployee;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.validator.ValidatorEmployee;
import lombok.NonNull;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface EmployeeService {
    Logger log = LoggerFactory.getLogger(DaoEmployee.class);

    static Report employeeService(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws ValidationException {
        try {
            val emp = new Employee(employee.getName().toUpperCase(), employee.getRole().toUpperCase(), employee.getDepartment());
            DaoEmployee.insert(jdbcTemplate, emp);
            return new Report("OK", "stored new employee " + emp.getName());
        } catch (DuplicateKeyException e) {
            log.warn("insert error {}", e);
            throw new ValidationException("Entity exists");
        } catch (Exception e) {
            log.error("insert error {}", e);
            throw new ValidationException(e.getMessage());
        }
    }

    static Report insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws ValidationException {
        val emp = new ValidatorEmployee(employee).validate();
        return employeeService(jdbcTemplate, emp);
    }

    static List<Employee> getByRole(@NonNull JdbcTemplate jdbcTemplate, @NonNull String role) {
        val l = DaoEmployee.readByRole(jdbcTemplate, role.toUpperCase());
        val s = l.stream().map(entity -> new Employee(entity));
        return s.collect(Collectors.toList());
    }

    static Optional<Employee> getById(@NonNull JdbcTemplate jdbcTemplate, @NonNull String name) {
        val e = DaoEmployee.readById(jdbcTemplate, name.toUpperCase());
        return e.map(entity -> new Employee(entity));
    }
}
