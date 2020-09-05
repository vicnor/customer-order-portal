package dk.apendo.customerorder.service.utils;

import dk.apendo.customerorder.repository.ORM.CustomerORM;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceUtils {

    public dk.apendo.customerorder.model.Customer asCustomer(CustomerORM customerORM) {
        dk.apendo.customerorder.model.Customer customer = new dk.apendo.customerorder.model.Customer();
        customer.setId(customerORM.getId());
        customer.setFirstName(customerORM.getFirstName());
        customer.setLastName(customerORM.getLastName());
        return customer;
    }

    public CustomerORM asCustomerORM(dk.apendo.customerorder.model.Customer customer) {
        CustomerORM customerORM = new CustomerORM();
        customerORM.setId(customer.getId());
        customerORM.setFirstName(customer.getFirstName());
        customerORM.setLastName(customer.getLastName());
        return customerORM;
    }

}
