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
@EqualsAndHashCode
final public class Employee {

    @With
    private String name;

    @With
    private String role;

    public Employee() {
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
