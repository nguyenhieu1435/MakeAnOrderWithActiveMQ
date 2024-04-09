package vn.edu.iuh.fit.backend.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Customer;

import java.util.Optional;

@Service
public interface ICustomerService {
    public Customer addCustomer(Customer customer);
    public Optional<Customer> findCustomerByEmail(String userName);
}
