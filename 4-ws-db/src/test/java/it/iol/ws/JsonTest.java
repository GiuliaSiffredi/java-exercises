package it.iol.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import it.iol.ws.util.JsonHelper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonTest {
    private static final Logger log = LoggerFactory.getLogger(JsonTest.class);

    private static Staff getStaff() {
        List<String> position = Arrays.asList("manager", "hr");

//        java.util.List<String> skills = new ArrayList<>(java.util.List.of("foo", "bar")); java 11
        java.util.List<String> skills = new ArrayList<>(Arrays.asList("foo", "bar"));

        Map<String, Integer> salary = new HashMap<>();
        salary.put("abc", 10000);
        salary.put("def", 20000);

        return new Staff("bob", 30, position, skills, salary);
    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void javaToJsonString() throws IOException {
        Staff staff = getStaff();
        String jsonString = JsonHelper.javaToString(staff);
        log.debug("jsonString: " + JsonHelper.javaToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}"));
    }

    /**
     * JSON string to java object
     *
     * @throws IOException
     */
    @Test
    public void jsonStringToJava() throws JsonProcessingException {
        Staff staff = getStaff();
        String jsonString = JsonHelper.javaToString(staff);
        Staff s = JsonHelper.stringToJava(jsonString, Staff.class);
        assert (s.toString().equals(staff.toString()));

    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void javaToJsonStringSalaryNull() throws JsonProcessingException {
        Staff staff = getStaff();
        staff.setSalary(null);
        String jsonString = JsonHelper.javaToString(staff);
        log.debug("jsonString: " + JsonHelper.javaToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":null}"));
    }

    /**
     * java object to jsonNode
     */
    @Test
    public void javaToJson() {
        Staff staff = getStaff();
        JsonNode json = JsonHelper.javaToJson(staff);
        log.debug(json.toString());
        assert ("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}".equals(json.toString()));
    }

    /**
     * jsonNode to java object
     */
    @Test
    public void jsonTojava() {
        Staff staff = getStaff();
        JsonNode json = JsonHelper.javaToJson(staff);
        log.debug(json.toString());
        Staff staff2 = JsonHelper.jsonToJava(json, Staff.class);
        assert (staff.equals(staff2));
    }
}