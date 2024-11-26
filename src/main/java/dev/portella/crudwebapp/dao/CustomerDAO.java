package dev.portella.crudwebapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.portella.crudwebapp.model.Customer;

public interface CustomerDAO {
    Page<Customer> findPaginated(Pageable pageable);

    Customer findById(Long id);

    List<Customer> findByField(String field, Object value);

    void create(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}
