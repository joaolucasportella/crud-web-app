package dev.portella.crudwebapp.validation;

import org.springframework.beans.factory.annotation.Autowired;

import dev.portella.crudwebapp.service.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private CustomerService customerService;

    private String field;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        return customerService.isUnique(field, value);
    }
}
