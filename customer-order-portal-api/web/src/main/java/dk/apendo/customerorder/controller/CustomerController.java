package dk.apendo.customerorder.controller;

import dk.apendo.customerorder.model.Customer;
import dk.apendo.customerorder.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("{id}")
    public Customer findById(@PathVariable Integer id) {
        return customerService.findById(id);
    }

    @PostMapping("")
    public Customer add(@RequestBody Customer customer) {
        Customer addedCustomer = customerService.add(customer);
        return addedCustomer;
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        customerService.deleteById(id);
    }

}
