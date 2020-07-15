package it.iol.ws.dao;

import io.vavr.control.Option;
import it.iol.ws.model.Employee;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * static methods to manage Employee table
 */
@Slf4j
public class DaoEmployee {

    private DaoEmployee() {
    }

    public static void insert(@NonNull JdbcTemplate jdbcTemplate, @NonNull Employee employee) throws DataAccessException {
        val sql = String.format("insert into employee(name,role) values('%s','%s')", employee.getName(), employee.getRole());
        log.debug(sql);
        jdbcTemplate.update(sql);
    }

    public static Option<EmployeeEntity> readById(@NonNull JdbcTemplate jdbcTemplate, @NonNull String name) {
        val sql = String.format("select name,role from employee where name = '%s'", name);
        log.debug(sql);
        List<EmployeeEntity> empList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeEntity.class));
        if (empList.size() == 0) return Option.none();
        assert (1 == empList.size());
        return Option.some(empList.get(0));
    }
}