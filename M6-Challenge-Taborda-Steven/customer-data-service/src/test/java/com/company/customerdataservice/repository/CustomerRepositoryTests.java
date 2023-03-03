package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepo;


    @Before
    public void setUp() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void addCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("aaa@gmail.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("aaaaa");
        customer.setAddress_2("bbbbb");
        customer.setCity("Las Cruces");
        customer.setState("New Mexico");
        customer.setPostal_code("88001");
        customer.setCountry("United States");

        //Act...
        customer = customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void getAllCustomers() {
        //Arrange...

        //Act...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("aaa@gmail.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("aaaaa");
        customer.setAddress_2("bbbbb");
        customer.setCity("Las Cruces");
        customer.setState("New Mexico");
        customer.setPostal_code("88001");
        customer.setCountry("United States");

        customerRepo.save(customer);

        Customer customer2 = new Customer();
        customer.setFirstName("Bob");
        customer.setLastName("Marley");
        customer.setEmail("ddd@gmail.com");
        customer.setPhone("555-333-3456");
        customer.setCompany("OtherCo");
        customer.setAddress_1("qweqeq");
        customer.setAddress_2("qwersf");
        customer.setCity("Dallas");
        customer.setState("Texas");
        customer.setPostal_code("88014");
        customer.setCountry("United States");

        customerRepo.save(customer2);

        List<Customer> customerList = customerRepo.findAll();

        //Assert...
        assertEquals(2, customerList.size());
    }

    @Test
    public void updateCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("aaa@gmail.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("aaaaa");
        customer.setAddress_2("bbbbb");
        customer.setCity("Las Cruces");
        customer.setState("New Mexico");
        customer.setPostal_code("88001");
        customer.setCountry("United States");

        customerRepo.save(customer);

        //Act...
        customer.setFirstName("UPDATED");

        customerRepo.save(customer);

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }

    @Test
    public void deleteCustomer() {
        //Arrange...
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Smith");
        customer.setEmail("aaa@gmail.com");
        customer.setPhone("111-222-3456");
        customer.setCompany("BigCo");
        customer.setAddress_1("aaaaa");
        customer.setAddress_2("bbbbb");
        customer.setCity("Las Cruces");
        customer.setState("New Mexico");
        customer.setPostal_code("88001");
        customer.setCountry("United States");
        customerRepo.save(customer);

        //Act...
        customerRepo.deleteById(customer.getId());

        //Assert...
        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }
}

