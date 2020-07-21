package it.iol.ws.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

/**
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@EqualsAndHashCode
final public class ErrorBean {

    private List<String> errors;

    private ErrorBean() {
    }

    public void setErrors(@NonNull List<String> errors) {
        this.errors = errors;
    }

    public ErrorBean(@NonNull String err) {
        //errors = List.of(err);  java 11
        errors = Arrays.asList(err);
    }

    public ErrorBean(@NonNull List<String> errors) {
        this.errors = errors;
    }

}