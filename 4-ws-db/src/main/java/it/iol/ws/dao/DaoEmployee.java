package it.iol.ws.dao;

import it.iol.ws.model.Employee;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Employee table
 * https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
 */
@Slf4j
public class DaoEmployee {

    private DaoEmployee() {
    }

    public static void insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws DataAccessException {
        String sql = "insert into employee(name,role,department) values(?,?,?)";
        log.debug(sql);
        jdbcTemplate.update(sql, employee.getName(), employee.getRole(), employee.getDepartment());
    }

    public static List<EmployeeEntity> readByRole(@NonNull JdbcTemplate jdbcTemplate, @NonNull String role) {
        String sql = String.format("select name,role,department from employee where role = '%s'", role);
        log.debug(sql);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeEntity.class));
    }

    public static Optional<EmployeeEntity> readById(@NonNull JdbcTemplate jdbcTemplate, @NonNull String name) {
        String sql = String.format("select name,role,department from employee where name = ?");
        log.debug(sql);
        try {
            EmployeeEntity emp = (EmployeeEntity) jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{name},
                    new BeanPropertyRowMapper(EmployeeEntity.class));
            return Optional.of(emp);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }
}