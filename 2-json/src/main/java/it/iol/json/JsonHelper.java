package it.iol.json;

/**
 * Giuseppe Cannella
 * Helper class for Json
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JsonHelper {

    ObjectMapper objectMapper = new ObjectMapper();

    static <T> JsonNode objectToJson(T o) {
        return objectMapper.valueToTree(o);
    }

    static <T> T stringToObject(String jsonString, Class<T> o) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, o);
    }

    static String objectToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    static String objectToPrettyString(Object o) {
        return objectToJson(o).toPrettyString();
    }

    static <T> T jsonToObject(JsonNode json, Class<T> o) {
        return objectMapper.convertValue(json, o);
    }

}