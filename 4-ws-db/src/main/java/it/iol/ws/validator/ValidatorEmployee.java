package it.iol.ws.validator;

import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import it.iol.ws.model.Employee;
import lombok.NonNull;

import java.util.List;

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
    public static Either<List<String>, Employee> validateEmployee(@NonNull Employee employee) {
        Validation<Seq<String>, Employee> v1 = Validation.combine(validateName(employee.getName()), validateRole(employee.getRole())).ap(Employee::new);
        Validation<List<String>, Employee> v2 = v1.mapError(e -> e.toJavaList());
        return v2.toEither();
    }

    private static Validation<String, String> validateName(@NonNull String name) {
//        return name.isBlank() ? Validation.invalid("name is empty") : Validation.valid(name); java 11
        return name.trim().isEmpty() ? Validation.invalid("name is empty") : Validation.valid(name);
    }

    private static Validation<String, String> validateRole(@NonNull String role) {
//        return role.isBlank() ? Validation.invalid("role is empty") : Validation.valid(role); java 11
        return role.trim().isEmpty() ? Validation.invalid("role is empty") : Validation.valid(role);
    }

}