package dk.apendo.customerorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.apendo.customerorder.SuperTest;
import dk.apendo.customerorder.model.Customer;
import dk.apendo.customerorder.model.Order;
import dk.apendo.customerorder.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

public class CustomerControllerTest extends SuperTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetById() throws Exception {

        Integer id = 1;
        String firstName = "Hans";
        String lastName = "Jensen";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Customer savedCustomer = customerRepository.save(customer);

        mvc.perform(get("/customers/" + savedCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedCustomer.getId())))
                .andExpect(jsonPath("$.firstName", is(savedCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(savedCustomer.getLastName())));

    }

    @Test
    public void testGetById_ExpectException() {
        Integer id = 999;

        assertThrows(NestedServletException.class,
                () ->{
                    mvc.perform(get("/customers/" + id))
                            .andExpect(status().isInternalServerError());
                });

    }

    @Test
    public void testDeleteById() throws Exception {
        String firstName = "Hans";
        String lastName = "Jensen";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Customer savedCustomer = customerRepository.save(customer);

        mvc.perform(delete("/customers/" + savedCustomer.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById_ExpectException() {
        Integer id = 999;

        assertThrows(NestedServletException.class,
                () ->{
                    mvc.perform(delete("/customers/" + id))
                            .andExpect(status().isInternalServerError());
                });
    }

    @Test
    public void testAdd() throws Exception {

        String firstName = "Hans";
        String lastName = "Jensen";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        Order order = new Order();
        order.setCreationDate(LocalDateTime.now());
        order.setCustomer(customer);

        customer.addOrder(order);

        Customer savedCustomer = customerRepository.save(customer);

        mvc.perform(post("/customers").content(mapper.writeValueAsString(customer)).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedCustomer.getId())))
                .andExpect(jsonPath("$.firstName", is(savedCustomer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(savedCustomer.getLastName())));
    }

}
