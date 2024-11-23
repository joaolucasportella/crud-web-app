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

        String cpf = customer.getCpf();
        String email = customer.getEmail();
        String phone = customer.getPhone();

        Boolean isCpfUnique = customerService.isUnique("cpf", cpf);
        Boolean isEmailUnique = customerService.isUnique("email", email);
        Boolean isPhoneUnique = customerService.isUnique("phone", phone);

        return isCpfUnique && isEmailUnique && isPhoneUnique;
    }
}
