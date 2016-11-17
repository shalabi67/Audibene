package com.shalabi.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.shalabi.com.shalabi.data.Customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class will provide a rest APIs for customers. it will provide the following functionality:
 * create a customer.
 * get customer appointments.
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomersController {

	@GetMapping()
    public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setFirstName("mohammad");
		customer.setLastName("shalabi");
		customer.setId(1);
		
		customers.add(customer);
        return customers;
    }
	@GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") @NotNull  Long id) {
		Customer customer = new Customer();
		customer.setFirstName("mohammad");
		customer.setLastName("shalabi");
		customer.setId(id);
        return customer;
    }
    @RequestMapping(method= RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customer;
    }
}
