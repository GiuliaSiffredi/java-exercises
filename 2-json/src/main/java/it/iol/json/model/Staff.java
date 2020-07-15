package it.iol.json.model;

import io.vavr.control.Option;

import java.util.Map;

public class Staff {

    private String name;
    private int age;
    private io.vavr.collection.List<String> position;   // vavr list
    private java.util.List<String> skills;              // java List
    private Option<Map<String, Integer>> salary;        // Map

    private Staff() {
    }

    public Staff(String name, int age, io.vavr.collection.List<String> position, java.util.List<String> skills, Option<Map<String, Integer>> salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.skills = skills;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position=" + position +
                ", skills=" + skills +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public io.vavr.collection.List<String> getPosition() {
        return position;
    }

    public void setPosition(io.vavr.collection.List<String> position) {
        this.position = position;
    }

    public java.util.List<String> getSkills() {
        return skills;
    }

    public void setSkills(java.util.List<String> skills) {
        this.skills = skills;
    }

    public Option<Map<String, Integer>> getSalary() {
        return salary;
    }

    public void setSalary(Option<Map<String, Integer>> salary) {
        this.salary = salary;
    }
}
