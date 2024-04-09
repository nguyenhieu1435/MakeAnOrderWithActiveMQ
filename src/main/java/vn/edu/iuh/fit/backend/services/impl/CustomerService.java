package vn.edu.iuh.fit.backend.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.repositories.ICustomerRepository;
import vn.edu.iuh.fit.backend.services.ICustomerService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private ICustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String userName) {
        return customerRepository.findCustomerByEmail(userName);
    }
}
