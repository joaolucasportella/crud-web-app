package dev.portella.crudwebapp.validation;

import org.springframework.beans.factory.annotation.Autowired;

import dev.portella.crudwebapp.model.Customer;
import dev.portella.crudwebapp.service.CustomerService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCustomerValidator implements ConstraintValidator<UniqueCustomer, Customer> {
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        Long id = customer.getId();

        if (id != null) {
            return true;
        }

        String email = customer.getEmail();
        String cpf = customer.getCpf();

        Boolean isEmailUnique = customerService.isUnique("email", email);
        Boolean isCpfUnique = customerService.isUnique("cpf", cpf);

        return isEmailUnique && isCpfUnique;
    }
}
