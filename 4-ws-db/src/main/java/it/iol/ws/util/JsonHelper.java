package it.iol.ws.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Giuseppe Cannella
 * Helper class for Json
 */

public class JsonHelper {
    private JsonHelper() {
    }

    public final static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> JsonNode javaToJson(T o) {
        return objectMapper.valueToTree(o);
    }

    public static <T> T stringToJava(String jsonString, Class<T> o) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, o);
    }

    public static String javaToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    public static String javaToPrettyString(Object o) {
        return javaToJson(o).toPrettyString();
    }

    public static <T> T jsonToJava(JsonNode json, Class<T> o) {
        return objectMapper.convertValue(json, o);
    }

}