package it.iol.ws;

import io.vavr.collection.Seq;
import io.vavr.control.Option;
import lombok.*;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@EqualsAndHashCode
public class Staff {

    @With
    private String name;

    @With
    private int age;

    @With
    private Seq<String> position;                   // vavr list

    @With
    private List<String> skills;                    // java List

    @With
    private Option<Map<String, Integer>> salary;    // Map

    private Staff() {
    }

    public Staff(@NonNull String name, int age, @NonNull Seq<String> position, @NonNull List<String> skills, @NonNull Option<Map<String, @NonNull Integer>> salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skills = skills;
        this.salary = salary;
    }
}
