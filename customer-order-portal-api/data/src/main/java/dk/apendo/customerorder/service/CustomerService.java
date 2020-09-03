package dk.apendo.customerorder.service;

import dk.apendo.customerorder.model.Customer;
import dk.apendo.customerorder.repository.CustomerRepository;
import dk.apendo.customerorder.repository.ORM.CustomerORM;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService implements CrudService<Customer, Integer> {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerORM> customerORMS = customerRepository.findAll();
        return customerORMS.stream().map(c -> asCustomer(c)).collect(Collectors.toList());
    }

    @Override
    public Customer findById(Integer id) {
        CustomerORM customerORM = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find customer with id " + id));
        return asCustomer(customerORM);
    }

    private Customer asCustomer(CustomerORM customerORM) {
        Customer customer = new Customer();
        customer.setId(customerORM.getId());
        customer.setFirstName(customerORM.getFirstName());
        customer.setLastName(customerORM.getLastName());
        return customer;
    }

    @Override
    public Customer add(Customer object) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
