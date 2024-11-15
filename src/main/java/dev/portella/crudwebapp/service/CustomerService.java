package dev.portella.crudwebapp.service;

import dev.portella.crudwebapp.dao.CustomerDAO;
import dev.portella.crudwebapp.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> findAll() {
        return customerDAO.findAll();
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
}
