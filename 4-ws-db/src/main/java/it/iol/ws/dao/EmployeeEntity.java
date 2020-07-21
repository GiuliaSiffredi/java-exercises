package it.iol.ws.dao;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class EmployeeEntity {

    private String name;
    private String role;

    private EmployeeEntity(){}

    public EmployeeEntity(@NonNull String name,@NonNull String role) {
        this.name = name;
        this.role = role;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setRole(@NonNull String role) {
        this.role = role;
    }

}