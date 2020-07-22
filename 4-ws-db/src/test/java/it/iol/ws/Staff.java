package it.iol.ws;


import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Staff {

    @NonNull
    private String name;

    private int age;

    @NonNull
    private List<String> position;                  // list

    @NonNull
    private List<String> skills;                    // List

    private Map<String, Integer> salary;    // Map

    private Staff() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    public List<String> getPosition() {
        return position;
    }

    public void setPosition(@NonNull List<String> position) {
        this.position = position;
    }

    @NonNull
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(@NonNull List<String> skills) {
        this.skills = skills;
    }

    public Map<String, Integer> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, Integer> salary) {
        this.salary = salary;
    }

    public Staff(String name, int age, List<String> position, List<String> skills, Map<String, Integer> salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skills = skills;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return age == staff.age &&
                name.equals(staff.name) &&
                position.equals(staff.position) &&
                skills.equals(staff.skills) &&
                Objects.equals(salary, staff.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, position, skills, salary);
    }
}
