package it.iol.ws.model;

import lombok.*;
import java.util.Arrays;
import java.util.List;

/**
 * The class is immutable - no setter
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@Setter
@EqualsAndHashCode
final public class ErrorBean {

    private List<String> errors;

    private ErrorBean() {
    }

    public ErrorBean(@NonNull String err) {
        //errors = List.of(err);  java 11
        errors = Arrays.asList(err);
    }

    public ErrorBean(@NonNull List<String> errors) {
        this.errors = errors;
    }

}