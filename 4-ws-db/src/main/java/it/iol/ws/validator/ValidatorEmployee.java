package it.iol.ws.validator;

import it.iol.ws.model.Employee;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * Validator class for Employee
 */
public class ValidatorEmployee {

    private ValidationException validationException = new ValidationException();
    private Employee employee;

    private ValidatorEmployee() {
    }

    public ValidatorEmployee(@NonNull Employee employee) {
        this.employee = employee;
    }

    private void roleValidator() {
        if (StringUtils.isBlank(employee.getRole())) validationException.add("role is empty");
    }

    private void nameValidator() {
        if (StringUtils.isBlank(employee.getName())) validationException.add("name is empty");else
        if (employee.getName().length() < 3) validationException.add("name is too short");
    }

    /**
     * returns an error List or the validated Employee
     */
    public Employee validate() throws ValidationException {

        nameValidator();
        roleValidator();

        if (validationException.isEmpty()) return employee;
        throw validationException;
    }

}
