package dk.apendo.customerorder.service;

import dk.apendo.customerorder.model.Customer;
import dk.apendo.customerorder.repository.CustomerRepository;
import dk.apendo.customerorder.service.utils.CustomerServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements CrudService<Customer, Integer> {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerServiceUtils customerServiceUtils) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find customer with id " + id));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 120, rollbackFor = Exception.class)
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find customer with id " + id));
        customerRepository.delete(customer);
    }

}
