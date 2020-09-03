package dk.apendo.customerorder.controller;

import dk.apendo.customerorder.model.Customer;
import dk.apendo.customerorder.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
