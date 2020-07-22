package it.iol.ws.dao;


import org.springframework.lang.NonNull;

public class EmployeeEntity {
    @NonNull
    private String name;
    @NonNull
    private String role;
    private String department;

    EmployeeEntity() {
    }

    public EmployeeEntity(String name, String role, String department) {
        this.name = name;
        this.role = role;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}