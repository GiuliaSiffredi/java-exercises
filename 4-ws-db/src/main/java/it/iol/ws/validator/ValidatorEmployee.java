package it.iol.ws.validator;

import it.iol.ws.ValidationException;
import it.iol.ws.model.Employee;

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
    public static Employee validateEmployee(Employee employee) throws ValidationException {
        ValidationException validationException = new ValidationException();
        if (employee.getName().trim().isEmpty()) validationException.add("name is empty");
        if (employee.getRole().trim().isEmpty()) validationException.add("role is empty");

        if (validationException.isEmpty()) return employee;
        throw validationException;
    }

}