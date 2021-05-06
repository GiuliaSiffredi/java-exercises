package it.iol.ws;

import com.fasterxml.jackson.databind.JsonNode;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.util.JsonHelper;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

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
        val emp = new Employee(randomName, "architect", null);
        val empJson = JsonHelper.objectToJson(emp);
        val request = new HttpEntity<JsonNode>(empJson, headers);

        val responseEntityStr = restTemplate.exchange("http://localhost:" + port + "/employee/3", HttpMethod.PUT, request, JsonNode.class);

        //check result
        assertEquals(responseEntityStr.getStatusCode().value(), 200);

        val expected = new Report("OK", "stored new employee " + randomName.toUpperCase());
        val s = JsonHelper.jsonToObject(responseEntityStr.getBody(), Report.class);
        assertEquals(s, expected);

        // read employee
        val res = new Employee(emp.getName().toUpperCase(), emp.getRole().toUpperCase(), null);
        val get = restTemplate.getForEntity("http://localhost:" + port + "/employee/" + randomName, JsonNode.class);
        assertEquals(200, get.getStatusCodeValue());
        val z = get.getBody();
        assertEquals(res, JsonHelper.jsonToObject(z, Employee.class));
    }

    @Test
    void employeeKO() {

        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        val emp = new Employee("", "   ", null);
        val empJson = JsonHelper.objectToJson(emp);
        val request = new HttpEntity<>(empJson, headers);//Object

        val responseEntityJson = restTemplate.exchange("http://localhost:" + port + "/employee/3", HttpMethod.PUT, request, JsonNode.class);

        //check result
        assertEquals(responseEntityJson.getStatusCode().value(), 400);

        val errors = JsonHelper.jsonToObject(responseEntityJson.getBody(), List.class);
        assertEquals("[name is empty, role is empty]", errors.toString());

    }

    @Test
    void employeeKO2() {

        val headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        val emp = new Employee("foo", "   ", null);
        val empJson = JsonHelper.objectToJson(emp);
        val request = new HttpEntity<>(empJson, headers);

        val responseEntityJson = restTemplate.exchange("http://localhost:" + port + "/employee/3", HttpMethod.PUT, request, JsonNode.class);

        //check result
        assertEquals(responseEntityJson.getStatusCode().value(), 400);
        val errors = JsonHelper.jsonToObject(responseEntityJson.getBody(), List.class);
        assertEquals("[role is empty]", errors.toString());

        // read employee must return 404
        val get = restTemplate.getForEntity("http://localhost:" + port + "/employee/foo", JsonNode.class);
        assertEquals(404, get.getStatusCodeValue());

    }
}
