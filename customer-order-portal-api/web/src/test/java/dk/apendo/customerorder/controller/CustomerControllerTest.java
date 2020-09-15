package dk.apendo.customerorder.controller;

import dk.apendo.customerorder.SuperTest;
import dk.apendo.customerorder.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.google.gson.Gson;

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

    @Test
    public void testGetById() throws Exception {

        Integer id = 1;
        String firstName = "Hans";
        String lastName = "Jensen";

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        mvc.perform(get("/customers/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)));

    }

    @Test
    public void testGetById_ExpectException() {
        Integer id = 1;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NestedServletException.class,
                () ->{
                    mvc.perform(get("/customers/" + id))
                            .andExpect(status().isInternalServerError());
                });

    }

    @Test
    public void testDeleteById() throws Exception {
        Integer id = 1;
        String firstName = "Hans";
        String lastName = "Jensen";

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        mvc.perform(delete("/customers/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById_ExpectException() {
        Integer id = 1;

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NestedServletException.class,
                () ->{
                    mvc.perform(get("/customers/" + id))
                            .andExpect(status().isInternalServerError());
                });
    }

    @Test
    public void testAdd() throws Exception {
        Integer id = 1;
        String firstName = "Hans";
        String lastName = "Jensen";

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        when(customerRepository.save(customer)).thenReturn(customer);

        mvc.perform(post("/customers").content(new Gson().toJson(customer)).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.firstName", is(firstName)))
                .andExpect(jsonPath("$.lastName", is(lastName)));
    }

}
