package dev.portella.crudwebapp.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.portella.crudwebapp.dao.CustomerDAO;
import dev.portella.crudwebapp.model.Customer;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Page<Customer> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerDAO.findPaginated(pageable);
    }

    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerDAO.findById(id));
    }

    public Customer save(Customer customer) {
        return customerDAO.save(customer);
    }

    public void deleteById(Long id) {
        customerDAO.deleteById(id);
    }

    public boolean isUnique(String field, String value) {
        return customerDAO.findByField(field, value).isEmpty();
    }
}
