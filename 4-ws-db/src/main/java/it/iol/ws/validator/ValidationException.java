package it.iol.ws.validator;

import lombok.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ValidationException extends Exception {

    private List<String> errors = new ArrayList<>();

    public void add(@NonNull String error) {
        this.errors.add(error);
    }

    public ValidationException() {
    }

    public ValidationException(@NonNull String err) {
        //errors = List.of(err);  java 11
        errors = Arrays.asList(err);
    }

    public ValidationException(@NonNull List<String> errors) {
        this.errors = errors;
    }

    public boolean isEmpty() {
        return errors.isEmpty();
    }
}

