package it.iol.ws.model;

import lombok.*;

/**
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@EqualsAndHashCode
final public class Report {

    private String status;

    private String desc;

    private Report() {
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    public Report(@NonNull String status, @NonNull String desc) {
        this.status = status;
        this.desc = desc;
    }

}