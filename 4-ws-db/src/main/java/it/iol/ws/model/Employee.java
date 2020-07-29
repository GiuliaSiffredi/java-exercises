package it.iol.ws.model;

import it.iol.ws.sql.EmployeeEntity;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
final public class Employee {

    @NonNull
    private String name;

    @NonNull
    private String role;

    private String department;

    private Employee() {
    }

    public Employee(@NonNull EmployeeEntity entity) {
        this.name = entity.getName();
        this.role = entity.getRole();
        this.department = entity.getDepartment();
    }

    public Employee(@NonNull String name, @NonNull String role, String department) {
        this.name = name;
        this.role = role;
        this.department = department;
    }

}
