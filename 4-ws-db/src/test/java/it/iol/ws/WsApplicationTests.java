package it.iol.ws;

import com.fasterxml.jackson.databind.JsonNode;
import it.iol.ws.model.Employee;
import it.iol.ws.model.ErrorBean;
import it.iol.ws.model.Report;
import it.iol.ws.util.JsonHelper;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WsApplicationTests extends BaseTestClass {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void employeeOK() {

        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        val randomName = RandomStringUtils.random(10, true, false);
        val emp = new Employee(randomName, "architect");
        val empJson = JsonHelper.javaToJson(emp);
        val request = new HttpEntity<>(empJson, headers);

        val responseEntityStr = restTemplate.postForEntity("http://localhost:" + port + "/employees/v1/3", request, JsonNode.class);

        //check result
        assertEquals(responseEntityStr.getStatusCode().value(), 200);

        val expected = new Report("OK", "stored new employee " + randomName.toUpperCase());
        val fromServer = responseEntityStr.getBody();
        val s = JsonHelper.jsonToJava(fromServer, Report.class);
        assertEquals(s, expected);

        // read employee
        val res = new Employee(emp.getName().toUpperCase(), emp.getRole().toUpperCase());
        val get = restTemplate.
                getForEntity("http://localhost:" + port + "/employees/v1/" + randomName, JsonNode.class);
        assertEquals(200, get.getStatusCodeValue());
        assertEquals(res, JsonHelper.jsonToJava(get.getBody(), Employee.class));
    }

    @Test
    void employeeKO() {

        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        val emp = new Employee("", "   ");
        val empJson = JsonHelper.javaToJson(emp);
        val request = new HttpEntity<>(empJson, headers);

        val responseEntityJson = restTemplate.
                postForEntity("http://localhost:" + port + "/employees/v1/3", request, JsonNode.class);

        //check result
        assertEquals(responseEntityJson.getStatusCode().value(), 400);

        val fromServer = responseEntityJson.getBody();
        val errors = JsonHelper.jsonToJava(fromServer, ErrorBean.class);
        assertEquals("[name is empty, role is empty]", errors.getErrors().toString());

    }

    @Test
    void employeeKO2() {

        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        val emp = new Employee("foo", "   ");
        val empJson = JsonHelper.javaToJson(emp);
        val request = new HttpEntity<>(empJson, headers);

        val responseEntityJson = restTemplate.
                postForEntity("http://localhost:" + port + "/employees/v1/3", request, JsonNode.class);

        //check result
        assertEquals(responseEntityJson.getStatusCode().value(), 400);
        val fromServer = responseEntityJson.getBody();
        val errors = JsonHelper.jsonToJava(fromServer, ErrorBean.class);
        assertEquals("[role is empty]", errors.getErrors().toString());

        // read employee must return 404
        val get = restTemplate.getForEntity("http://localhost:" + port + "/employees/v1/foo", JsonNode.class);
        assertEquals(404, get.getStatusCodeValue());

    }
}
