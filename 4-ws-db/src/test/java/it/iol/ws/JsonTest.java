package it.iol.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.control.Option;
import it.iol.ws.util.JsonHelper;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Log4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonTest {

    private static Staff getStaff() {
        io.vavr.collection.List<String> position = io.vavr.collection.List.of("manager", "hr");

        java.util.List<String> skills = new ArrayList<>(java.util.List.of("foo", "bar"));

        Map<String, Integer> salary = new HashMap<>();
        salary.put("abc", 10000);
        salary.put("def", 20000);

        return new Staff("bob", 30, position, skills, Option.some(salary));
    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void javaToJsonString() throws IOException {
        val staff = getStaff();
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
        val staff = getStaff();
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
        val staff = getStaff().withSalary(Option.none());
        String jsonString = JsonHelper.javaToString(staff);
        log.debug("jsonString: " + JsonHelper.javaToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":null}"));
    }

    /**
     * java object to jsonNode
     */
    @Test
    public void javaToJson() {
        val staff = getStaff();
        JsonNode json = JsonHelper.javaToJson(staff);
        log.debug(json.toString());
        assert ("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}".equals(json.toString()));
    }

    /**
     * jsonNode to java object
     */
    @Test
    public void jsonTojava() {
        val staff = getStaff();
        JsonNode json = JsonHelper.javaToJson(staff);
        log.debug(json.toString());
        val staff2 = JsonHelper.jsonToJava(json, Staff.class);
        assert (staff.equals(staff2));
    }
}