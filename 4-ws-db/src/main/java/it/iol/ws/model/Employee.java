package it.iol.ws.model;

import it.iol.ws.dao.EmployeeEntity;
import lombok.*;

/**
 * The class is immutable - no setter
 * no default constructor
 * all attributes are not null
 */
@ToString
@Getter
@Setter
@EqualsAndHashCode
final public class Employee {

    private String name;

    private String role;

    private Employee() {
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
