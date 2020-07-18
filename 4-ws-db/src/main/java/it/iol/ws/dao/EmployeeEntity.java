package it.iol.ws.dao;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class EmployeeEntity {

    private String name;
    private String role;

    public EmployeeEntity(@NonNull String name,@NonNull String role) {
        this.name = name;
        this.role = role;
    }

    private EmployeeEntity(){}
}