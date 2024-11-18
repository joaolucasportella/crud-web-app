package dev.portella.crudwebapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.portella.crudwebapp.model.Customer;

public interface CustomerDAO {
    Page<Customer> findPaginated(Pageable pageable);

    Customer findById(Long id);

    Customer save(Customer customer);

    void deleteById(Long id);
}
