package it.iol.ws.model;

import org.springframework.lang.NonNull;

import java.util.Objects;

/**
 * no default constructor
 * all attributes are not null
 */
final public class Report {

    @NonNull
    private String status;

    @NonNull
    private String desc;

    private Report() {
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }

    @NonNull
    public String getDesc() {
        return desc;
    }

    public void setDesc(@NonNull String desc) {
        this.desc = desc;
    }

    public Report(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return status.equals(report.status) &&
                desc.equals(report.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, desc);
    }
}