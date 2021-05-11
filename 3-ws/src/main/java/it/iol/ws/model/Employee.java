package it.iol.ws.model;

public class Employee {

    private String name;
    private String role;

    private Employee() {
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
