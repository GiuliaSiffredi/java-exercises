package it.iol.ws;

import it.iol.ws.service.EmployeeService;
import it.iol.ws.sql.EmployeeEntity;
import it.iol.ws.util.IApplicationProperties;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Configuration
@EnableScheduling
public class Cron {

    @Autowired
    private IApplicationProperties applicationProperties;
    Logger log = LoggerFactory.getLogger(Cron.class);

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedRateTask() throws IOException {

        String sql = "select id,name,role,department from employee";
        val list = new ArrayList<EmployeeEntity>();
        SqlRowSet set = jdbcTemplate.queryForRowSet(sql);
        while (set.next()) {
            val constraint = new EmployeeEntity();
            constraint.setId((UUID)set.getObject(1));
            constraint.setName(set.getString(2));
            constraint.setRole(set.getString(3));
            constraint.setDepartment(set.getString(4));
            list.add(constraint);
            if (list.size() == 1000) {
                log.debug("append in csv file n records: {}" + list.size());
                //EmployeeService.appendToCsv(csvFile, list);
                EmployeeService.appendToCsv(applicationProperties.getFileOut(), list);
                list.clear();
            }
        }
        log.debug("ultimo chunk, append in csv file n records: {}" + list.size());
        //EmployeeService.appendToCsv(csvFile, list);
        EmployeeService.appendToCsv(applicationProperties.getFileOut(), list);

        //System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
