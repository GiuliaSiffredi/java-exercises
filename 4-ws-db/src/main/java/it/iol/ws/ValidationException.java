package it.iol.ws;

import org.springframework.lang.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationException extends Exception {
    @NonNull

    private List<String> errors = new ArrayList<>();

    private boolean empty;

    public boolean isEmpty() {
        return errors.isEmpty();
    }

    public void setErrors( List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void add(String error) {
        this.errors.add(error);
    }

    public ValidationException() {
    }

    public ValidationException(@NonNull String err) {
        //errors = List.of(err);  java 11
        errors = Arrays.asList(err);
    }

    public ValidationException(List<String> errors) {
        this.errors = errors;
    }


}

