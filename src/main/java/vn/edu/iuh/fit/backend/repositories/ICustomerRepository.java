package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.backend.models.Customer;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    public Optional<Customer> findCustomerByEmail(String email);
}
