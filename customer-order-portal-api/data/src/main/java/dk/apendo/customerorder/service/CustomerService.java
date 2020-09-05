package dk.apendo.customerorder.service;

import dk.apendo.customerorder.repository.CustomerRepository;
import dk.apendo.customerorder.repository.ORM.CustomerORM;
import dk.apendo.customerorder.service.utils.CustomerServiceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements CrudService<dk.apendo.customerorder.model.Customer, Integer> {

    private CustomerRepository customerRepository;

    private CustomerServiceUtils customerServiceUtils;

    public CustomerService(CustomerRepository customerRepository, CustomerServiceUtils customerServiceUtils) {
        this.customerRepository = customerRepository;
        this.customerServiceUtils = customerServiceUtils;
    }

    @Override
    public List<dk.apendo.customerorder.model.Customer> findAll() {
        List<CustomerORM> customerORMS = customerRepository.findAll();
        return customerORMS.stream().map(c -> customerServiceUtils.asCustomer(c)).collect(Collectors.toList());
    }

    @Override
    public dk.apendo.customerorder.model.Customer findById(Integer id) {
        CustomerORM customerORM = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find customer with id " + id));
        return customerServiceUtils.asCustomer(customerORM);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 120, rollbackFor = Exception.class)
    public dk.apendo.customerorder.model.Customer add(dk.apendo.customerorder.model.Customer object) {
        CustomerORM customerORM = customerRepository.save(customerServiceUtils.asCustomerORM(object));
        return customerServiceUtils.asCustomer(customerORM);
    }

    @Override
    public void deleteById(Integer id) {
        CustomerORM customerORM = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find customer with id " + id));
        customerRepository.delete(customerORM);
    }

}
