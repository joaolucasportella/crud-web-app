package dev.portella.crudwebapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import dev.portella.crudwebapp.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class JpaCustomerDAO implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private CriteriaBuilder cb;
    private CriteriaQuery<Customer> cq;
    private Root<Customer> root;

    private void initializeCriteriaQuery() {
        cb = entityManager.getCriteriaBuilder();
        cq = cb.createQuery(Customer.class);
        root = cq.from(Customer.class);
    }

    @Override
    public Page<Customer> findPaginated(Pageable pageable) {
        initializeCriteriaQuery();
        cq.select(root);

        TypedQuery<Customer> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Customer> countRoot = countQuery.from(Customer.class);
        countQuery.select(cb.count(countRoot));
        long totalRecords = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(query.getResultList(), pageable, totalRecords);
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findByField(String field, Object value) {
        initializeCriteriaQuery();

        Predicate predicate = cb.equal(root.get(field), value);
        cq.where(predicate);

        TypedQuery<Customer> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }

        return customer;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Customer customer = findById(id);

        if (customer != null) {
            entityManager.remove(customer);
        }
    }
}
