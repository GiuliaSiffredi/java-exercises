package it.iol.ws.validator;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import it.iol.ws.model.Employee;
import lombok.NonNull;

/**
 * static methods to validate Employee objects
 */
public class ValidatorEmployee {

    private ValidatorEmployee() {
    }

    /**
     * returns an error List or the validated Employee
     *
     * @param employee
     * @return
     */
    public static Validation<Seq<String>, Employee> validateEmployee(@NonNull Employee employee) {
        return Validation.combine(validateName(employee.getName()), validateRole(employee.getRole())).ap(Employee::new);
    }

    private static Validation<String, String> validateName(@NonNull String name) {
        return name.isBlank() ? Validation.invalid("name is empty") : Validation.valid(name);
    }

    private static Validation<String, String> validateRole(@NonNull String role) {
        return role.isBlank() ? Validation.invalid("role is empty") : Validation.valid(role);
    }

}