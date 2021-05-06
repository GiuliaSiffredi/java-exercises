package it.iol.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.iol.ws.util.JsonHelper;
import lombok.val;
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
        val position = Arrays.asList("manager", "hr");

//      val skills = List.of("foo", "bar"); //java 11
        val skills = new ArrayList<>(Arrays.asList("foo", "bar"));

        val salary = new HashMap<String, Integer>();
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
        val staff = getStaff();
        val jsonString = JsonHelper.objectToString(staff);
        log.debug("jsonString: {}", JsonHelper.objectToPrettyString(staff));

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
        val jsonString = JsonHelper.objectToString(staff);
        val s = JsonHelper.stringToObject(jsonString, Staff.class);
        assert (s.toString().equals(staff.toString()));

    }

    /**
     * Java object to JSON string
     *
     * @throws IOException
     */
    @Test
    public void javaToJsonStringSalaryNull() throws JsonProcessingException {
        val staff = getStaff();
        staff.setSalary(null);
        val jsonString = JsonHelper.objectToString(staff);
        log.debug("jsonString: {}", JsonHelper.objectToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":null}"));
    }

    /**
     * java object to jsonNode
     */
    @Test
    public void javaToJson() {
        val staff = getStaff();
        val json = JsonHelper.objectToJson(staff);
        log.debug(json.toString());
        assert ("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}".equals(json.toString()));
    }

    /**
     * jsonNode to java object
     */
    @Test
    public void jsonTojava() {
        val staff = getStaff();
        val json = JsonHelper.objectToJson(staff);
        log.debug(json.toString());
        val staff2 = JsonHelper.jsonToObject(json, Staff.class);
        assert (staff.equals(staff2));
    }
}
