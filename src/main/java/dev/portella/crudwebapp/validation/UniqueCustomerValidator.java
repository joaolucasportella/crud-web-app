package dev.portella.crudwebapp.validation;

import org.springframework.beans.factory.annotation.Autowired;

import dev.portella.crudwebapp.model.Customer;
import dev.portella.crudwebapp.service.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCustomerValidator implements ConstraintValidator<UniqueCustomer, Customer> {
    @Autowired
    private CustomerService customerService;

    private boolean validateUniqueness(Long id, String field, String value, String errorMessage, String propertyNode,
            ConstraintValidatorContext context) {
        if (!customerService.isUnique(id, field, value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addPropertyNode(propertyNode)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        Long id = customer.getId();
        String cpf = customer.getCpf();
        String email = customer.getEmail();
        String phone = customer.getPhone();

        boolean isValid = true;

        isValid &= validateUniqueness(id, "cpf", cpf, "{customer.cpf.notUnique}", "cpf", context);

        isValid &= validateUniqueness(id, "email", email, "{customer.email.notUnique}", "email", context);

        isValid &= validateUniqueness(id, "phone", phone, "{customer.phone.notUnique}", "phone", context);

        return isValid;
    }
}
