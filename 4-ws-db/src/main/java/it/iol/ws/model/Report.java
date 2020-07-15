package it.iol.ws.model;

import lombok.*;

/**
 * The class is immutable - no setter
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@EqualsAndHashCode
final public class Report {

    @With
    private String status;

    @With
    private String desc;

    private Report() {
    }

    public Report(@NonNull String status,@NonNull String desc) {
        this.status = status;
        this.desc = desc;
    }

}