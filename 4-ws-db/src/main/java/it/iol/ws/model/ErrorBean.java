package it.iol.ws.model;

import io.vavr.collection.Seq;
import lombok.*;

import java.util.List;

/**
 * The class is immutable - no setter
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@EqualsAndHashCode
final public class ErrorBean {

    @With
    private List<String> errors;

    private ErrorBean() {
    }

    public ErrorBean(@NonNull String err) {
        errors = List.of(err);
    }

    public ErrorBean(@NonNull List<String> errors) {
        this.errors = errors;
    }

    public ErrorBean(@NonNull Seq<String> errors) {
        this.errors = errors.toJavaList();
    }

}