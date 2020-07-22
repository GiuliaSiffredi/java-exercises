package it.iol.ws;

import com.fasterxml.jackson.databind.JsonNode;
import it.iol.ws.model.Employee;
import it.iol.ws.model.Report;
import it.iol.ws.util.JsonHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WsApplicationTests extends BaseTestClass {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void employeeOK() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String randomName = RandomStringUtils.random(10, true, false);
        Employee emp = new Employee(randomName, "architect", null);
        JsonNode empJson = JsonHelper.javaToJson(emp);
        HttpEntity<JsonNode> request = new HttpEntity<>(empJson, headers);

        ResponseEntity<JsonNode> responseEntityStr = restTemplate.postForEntity("http://localhost:" + port + "/employee/3", request, JsonNode.class);

        //check result
        assertEquals(responseEntityStr.getStatusCode().value(), 200);

        Report expected = new Report("OK", "stored new employee " + randomName.toUpperCase());
        Report s = JsonHelper.jsonToJava( responseEntityStr.getBody(), Report.class);
        assertEquals(s, expected);

        // read employee
        Employee res = new Employee(emp.getName().toUpperCase(), emp.getRole().toUpperCase(), null);
        ResponseEntity<JsonNode> get = restTemplate.
                getForEntity("http://localhost:" + port + "/employee/" + randomName, JsonNode.class);
        assertEquals(200, get.getStatusCodeValue());
        JsonNode z = get.getBody();
        assertEquals(res, JsonHelper.jsonToJava(z, Employee.class));
    }

    @Test
    void employeeKO() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Employee emp = new Employee("", "   ", null);
        Object empJson = JsonHelper.javaToJson(emp);
        HttpEntity<Object> request = new HttpEntity<>(empJson, headers);

        ResponseEntity<JsonNode> responseEntityJson = restTemplate.
                postForEntity("http://localhost:" + port + "/employee/3", request, JsonNode.class);

        //check result
        assertEquals(responseEntityJson.getStatusCode().value(), 400);

        List errors = JsonHelper.jsonToJava(responseEntityJson.getBody(), List.class);
        assertEquals("[name is empty, role is empty]", errors.toString());

    }

    @Test
    void employeeKO2() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Employee emp = new Employee("foo", "   ", null);
        Object empJson = JsonHelper.javaToJson(emp);
        HttpEntity<Object> request = new HttpEntity<>(empJson, headers);

        ResponseEntity<JsonNode> responseEntityJson = restTemplate.
                postForEntity("http://localhost:" + port + "/employee/3", request, JsonNode.class);

        //check result
        assertEquals(responseEntityJson.getStatusCode().value(), 400);
        List<String> errors = JsonHelper.jsonToJava(responseEntityJson.getBody(), List.class);
        assertEquals("[role is empty]", errors.toString());

        // read employee must return 404
        ResponseEntity<JsonNode> get = restTemplate.getForEntity("http://localhost:" + port + "/employee/foo", JsonNode.class);
        assertEquals(404, get.getStatusCodeValue());

    }
}
