package dev.portella.crudwebapp.service;

import java.util.List;
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
        if (size > 20 || size < 1) {
            size = 15;
        }

        Pageable pageable = PageRequest.of(page, size);
        return customerDAO.findPaginated(pageable);
    }

    public Optional<Customer> findById(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }

        try {
            Long parsedId = Long.parseLong(id);
            return Optional.ofNullable(customerDAO.findById(parsedId));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public Customer findByIdOrThrow(String id) {
        return findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }

    public void save(Customer customer) {
        if (customer.getId() == null) {
            customerDAO.create(customer);
        } else {
            customerDAO.update(customer);
        }
    }

    public void deleteById(String id) {
        Customer customer = findByIdOrThrow(id);
        customerDAO.delete(customer);
    }

    public boolean isUnique(Long id, String field, String value) {
        List<Customer> customers = customerDAO.findByField(field, value);
        customers.removeIf(customer -> customer.getId().equals(id));

        return customers.isEmpty();
    }
}
