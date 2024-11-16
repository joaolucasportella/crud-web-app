package dev.portella.crudwebapp.dao;

import java.util.List;

import dev.portella.crudwebapp.model.Customer;

public interface CustomerDAO {
    List<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);

    void deleteById(Long id);
}
