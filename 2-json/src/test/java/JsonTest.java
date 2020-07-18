import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.vavr.control.Option;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import it.iol.json.JsonHelper;
import it.iol.json.model.Staff;
import org.junit.jupiter.api.Test;

public class JsonTest {
    private static Staff getStaff() {
        io.vavr.collection.List<String> position = io.vavr.collection.List.of("manager", "hr");

        java.util.List<String> skills = Arrays.asList("foo", "bar");

        Map<String, Integer> salary = new HashMap();
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
        Staff staff = getStaff();
        String jsonString = JsonHelper.javaToString(staff);
        System.out.println("jsonString: " + JsonHelper.javaToPrettyString(staff));

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
        staff.setSalary(Option.none());
        String jsonString = JsonHelper.javaToString(staff);
        System.out.println("jsonString: " + JsonHelper.javaToPrettyString(staff));

        assert (jsonString.equals("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":null}"));
    }

    /**
     * java object to jsonNode
     */
    @Test
    public void javaToJson() {
        Staff staff = getStaff();
        JsonNode json = JsonHelper.javaToJson(staff);
        System.out.println(json.toString());
        assert ("{\"name\":\"bob\",\"age\":30,\"position\":[\"manager\",\"hr\"],\"skills\":[\"foo\",\"bar\"],\"salary\":{\"abc\":10000,\"def\":20000}}".equals(json.toString()));
    }

    /**
     * jsonNode to java object
     */
    @Test
    public void jsonTojava() {
        Staff staff = getStaff();
        JsonNode json = JsonHelper.javaToJson(staff);
        System.out.println(json.toString());
        Staff staff2 = JsonHelper.jsonToJava(json, Staff.class);
        assert (staff.equals(staff2));
    }
}