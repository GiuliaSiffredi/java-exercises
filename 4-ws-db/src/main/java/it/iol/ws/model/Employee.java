package it.iol.ws.model;

import it.iol.ws.dao.EmployeeEntity;
import lombok.*;

/**
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@EqualsAndHashCode
final public class Employee {

    private String name;

    private String role;

    private Employee() {
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setRole(@NonNull String role) {
        this.role = role;
    }

    public Employee(@NonNull EmployeeEntity entity) {
        this.name = entity.getName();
        this.role = entity.getRole();
    }

    public Employee(@NonNull String name, @NonNull String role) {
        this.name = name;
        this.role = role;
    }
}
