package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@example.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("123 Main St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setPostal_code("12345");
        customer.setCountry("USA");
    }

    @Test
    public void getCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Joe"))
                .andExpect(jsonPath("$[0].lastName").value("Smith"))
                .andExpect(jsonPath("$[0].email").value("joe.smith@example.com"))
                .andExpect(jsonPath("$[0].phone").value("111-222-3456"))
                .andExpect(jsonPath("$[0].company").value("BigCo"))
                .andExpect(jsonPath("$[0].address_1").value("123 Main St"))
                .andExpect(jsonPath("$[0].city").value("Anytown"))
                .andExpect(jsonPath("$[0].state").value("CA"))
                .andExpect(jsonPath("$[0].postal_code").value("12345"))
                .andExpect(jsonPath("$[0].country").value("USA"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@example.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("123 Main St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setPostal_code("12345");
        customer.setCountry("USA");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customers/1"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        Customer responseCustomer = objectMapper.readValue(content, Customer.class);
        assertEquals(customer, responseCustomer);
    }

    @Test
    public void testAddCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@example.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("123 Main St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setPostal_code("12345");
        customer.setCountry("USA");
        ObjectMapper objectMapper = new ObjectMapper();
        String customerJson = objectMapper.writeValueAsString(customer);

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        Customer responseCustomer = objectMapper.readValue(content, Customer.class);
        assertNotNull(responseCustomer.getId());
        assertEquals(customer.getFirstName(), responseCustomer.getFirstName());
        assertEquals(customer.getLastName(), responseCustomer.getLastName());
        assertEquals(customer.getEmail(), responseCustomer.getEmail());
        assertEquals(customer.getPhone(), responseCustomer.getPhone());
        assertEquals(customer.getCompany(), responseCustomer.getCompany());
        assertEquals(customer.getAddress_1(), responseCustomer.getAddress_1());
        assertEquals(customer.getCity(), responseCustomer.getCity());
        assertEquals(customer.getState(), responseCustomer.getState());
        assertEquals(customer.getPostal_code(), responseCustomer.getPostal_code());
        assertEquals(customer.getCountry(), responseCustomer.getCountry());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@example.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("123 Main St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setPostal_code("12345");
        customer.setCountry("USA");
        customerRepository.save(customer);

        // Act
        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/" + customer.getId()))
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Customer> deletedCustomer = customerRepository.findById(customer.getId());

        // Assert
        assertFalse(deletedCustomer.isPresent());
    }


    @Test
    public void testUpdateCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("joe.smith@example.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("123 Main St");
        customer.setCity("Anytown");
        customer.setState("CA");
        customer.setPostal_code("12345");
        customer.setCountry("USA");
        customerRepository.save(customer);

        customer.setFirstName("Updated");
        customer.setEmail("updated@example.com");

        JSONObject customerJson = new JSONObject();
        customerJson.put("id", customer.getId());
        customerJson.put("firstName", customer.getFirstName());
        customerJson.put("lastName", customer.getLastName());
        customerJson.put("email", customer.getEmail());
        customerJson.put("phone", customer.getPhone());
        customerJson.put("company", customer.getCompany());
        customerJson.put("address_1", customer.getAddress_1());
        customerJson.put("city", customer.getCity());
        customerJson.put("state", customer.getState());
        customerJson.put("postal_code", customer.getPostal_code());
        customerJson.put("country", customer.getCountry());

        // Act
        mockMvc.perform(MockMvcRequestBuilders.put("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson.toString()))
                .andExpect(status().isNoContent())
                .andReturn();

        Optional<Customer> updatedCustomer = customerRepository.findById(customer.getId());

        // Assert
        assertTrue(updatedCustomer.isPresent());
        assertEquals("Updated", updatedCustomer.get().getFirstName());
        assertEquals("updated@example.com", updatedCustomer.get().getEmail());
    }

}

