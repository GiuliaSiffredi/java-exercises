package it.iol.json.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Staff {

    private String name;
    private int age;
    private List<String> position;                      // list
    private List<String> skills;                        // List
    private Map<String, Integer> salary;                // Map

    private Staff() {
    }

    public Staff(String name, int age, List<String> position, List<String> skills, Map<String, Integer> salary) {
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

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Map<String, Integer> getSalary() {
        return salary;
    }

    public void setSalary(Map<String, Integer> salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return age == staff.age &&
                Objects.equals(name, staff.name) &&
                Objects.equals(position, staff.position) &&
                Objects.equals(skills, staff.skills) &&
                Objects.equals(salary, staff.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, position, skills, salary);
    }
}
