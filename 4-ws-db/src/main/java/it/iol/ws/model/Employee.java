package it.iol.ws.model;

import it.iol.ws.dao.EmployeeEntity;
import org.springframework.lang.NonNull;

import java.util.Objects;


/**
 * no default constructor
 * all attributes are not null
 */

final public class Employee {

    @NonNull
    private String name;

    @NonNull
    private String role;

    private String department;

    private Employee() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getRole() {
        return role;
    }

    public void setRole(@NonNull String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee(EmployeeEntity entity) {
        this.name = entity.getName();
        this.role = entity.getRole();
        this.department = entity.getDepartment();
    }

    public Employee(String name, String role, String department) {
        this.name = name;
        this.role = role;
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) &&
                role.equals(employee.role) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, department);
    }
}
