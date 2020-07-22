package it.iol.ws.validator;

import it.iol.ws.model.Employee;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

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
    public static Employee validateEmployee(@NonNull Employee employee) throws ValidationException {
        ValidationException validationException = new ValidationException();
        if (StringUtils.isBlank(employee.getName())) validationException.add("name is empty");
        if (StringUtils.isBlank(employee.getRole())) validationException.add("role is empty");

        if (validationException.isEmpty()) return employee;
        throw validationException;
    }

}