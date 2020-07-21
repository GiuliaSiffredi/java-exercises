package it.iol.ws;

import io.vavr.control.Option;
import lombok.*;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@EqualsAndHashCode
public class Staff {

    private String name;

    private int age;

    private List<String> position;                  // list

    private List<String> skills;                    // List

    private Option<Map<String, Integer>> salary;    // Map

    private Staff() {
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(@NonNull List<String> position) {
        this.position = position;
    }

    public void setSkills(@NonNull List<String> skills) {
        this.skills = skills;
    }

    public void setSalary(@NonNull Option<Map<String, Integer>> salary) {
        this.salary = salary;
    }

    public Staff(@NonNull String name, int age, @NonNull List<String> position, @NonNull List<String> skills, @NonNull Option<Map<String, @NonNull Integer>> salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skills = skills;
        this.salary = salary;
    }
}
