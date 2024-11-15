package dev.portella.crudwebapp.dao;

import dev.portella.crudwebapp.model.Customer;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    void deleteById(Long id);
}
