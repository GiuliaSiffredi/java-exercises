package it.iol.ws.service;

import io.vavr.control.Either;
import io.vavr.control.Option;
import it.iol.ws.dao.DaoEmployee;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.model.ErrorBean;
import it.iol.ws.validator.ValidatorEmployee;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Slf4j
public class EmployeeService {

    private EmployeeService() {
    }

    private static Either<ErrorBean, Report> employeeService(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) {
        try {
            val emp = new Employee(employee.getName().toUpperCase(), employee.getRole().toUpperCase());
            DaoEmployee.insert(jdbcTemplate, emp);
            return Either.right(new Report("OK", "stored new employee " + emp.getName()));
        } catch (Exception e) {
            log.error("insert error", e);
            return Either.left(new ErrorBean(e.getMessage()));
        }
    }

    public static Either<ErrorBean, Report> insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) {

        Either<List<String>, Employee> validator = ValidatorEmployee.validateEmployee(employee);

        if (validator.isLeft()) return Either.left(new ErrorBean(validator.getLeft()));
        return (employeeService(jdbcTemplate, validator.get()));
    }

    public static Option<Employee> get(JdbcTemplate jdbcTemplate, String name) {
        val e = DaoEmployee.readById(jdbcTemplate, name.toUpperCase());
        return e.map(entity -> new Employee(entity));
    }
}
