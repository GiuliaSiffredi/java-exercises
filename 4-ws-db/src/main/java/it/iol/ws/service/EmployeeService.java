package it.iol.ws.service;

import it.iol.ws.ValidationException;
import it.iol.ws.dao.DaoEmployee;
import it.iol.ws.dao.EmployeeEntity;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.validator.ValidatorEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeService {
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    private EmployeeService() {
    }

    private static Report employeeService(@NonNull JdbcTemplate jdbcTemplate, Employee employee) throws ValidationException {
        try {
            Employee emp = new Employee(employee.getName().toUpperCase(), employee.getRole().toUpperCase(), employee.getDepartment());
            DaoEmployee.insert(jdbcTemplate, emp);
            return new Report("OK", "stored new employee " + emp.getName());
        } catch (DuplicateKeyException e) {
            log.error("insert error", e);
            throw new ValidationException("Entity exists");
        } catch (Exception e) {
            log.error("insert error", e);
            throw new ValidationException(e.getMessage());
        }
    }

    public static Report insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws ValidationException {
        Employee emp = ValidatorEmployee.validateEmployee(employee);
        return employeeService(jdbcTemplate, emp);
    }

    public static List<Employee> getByRole(JdbcTemplate jdbcTemplate, String role) {
        List<EmployeeEntity> l = DaoEmployee.readByRole(jdbcTemplate, role.toUpperCase());
        Stream<Employee> s = l.stream().map(entity -> new Employee(entity));
        return s.collect(Collectors.toList());
    }

    public static Optional<Employee> getById(JdbcTemplate jdbcTemplate, String name) {
        Optional<EmployeeEntity> e = DaoEmployee.readById(jdbcTemplate, name.toUpperCase());
        return e.map(entity -> new Employee(entity));
    }
}
