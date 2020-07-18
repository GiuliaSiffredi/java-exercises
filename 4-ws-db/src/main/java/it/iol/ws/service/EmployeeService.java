package it.iol.ws.service;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import it.iol.ws.dao.DaoEmployee;
import it.iol.ws.dao.EmployeeEntity;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.model.ErrorBean;
import it.iol.ws.validator.ValidatorEmployee;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Invalid;
import static io.vavr.Patterns.$Valid;

@Slf4j
public class EmployeeService {

    private EmployeeService() {
    }

    private static Either<ErrorBean, Report> withError(@NonNull Seq<String> e) {
        log.error("validator error: {}", e);
        return Either.left(new ErrorBean(e));
    }

    private static Either<ErrorBean, Report> withoutError(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) {
        try {
            val emp = new Employee(employee.getName().toUpperCase(),employee.getRole().toUpperCase());
            DaoEmployee.insert(jdbcTemplate, emp);
            return Either.right(new Report("OK", "stored new employee " + emp.getName()));
        } catch (Exception e) {
            log.error("insert error", e);
            return Either.left(new ErrorBean(e.getMessage()));
        }
    }

    public static Either<ErrorBean, Report> insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) {

        Validation<Seq<String>, Employee> err = ValidatorEmployee.validateEmployee(employee);

        return Match(err).of(
                Case($Invalid($()), e -> withError(e)),
                Case($Valid($()), emp -> withoutError(jdbcTemplate, emp))
        );
    }

    public static Option<Employee> get(JdbcTemplate jdbcTemplate, String name) {
        val e = DaoEmployee.readById(jdbcTemplate, name.toUpperCase());
        return e.map(entity -> new Employee(entity));
    }
}
