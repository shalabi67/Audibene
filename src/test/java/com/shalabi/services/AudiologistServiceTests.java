package com.shalabi.services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shalabi.AudibeneApplication;
import com.shalabi.data.Customer;
import com.shalabi.data.IAudiologistRepository;
import com.shalabi.data.ICustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AudibeneApplication.class)
public class AudiologistServiceTests {
	@Autowired
	AudiologistService service;
	
	@Autowired
	IAudiologistRepository repository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	
	@Test
	public void testAddCustomer() {
		Customer customer = create();
		
		service.addCustomer(3L, customer);		
		Assert.assertTrue(customer.getId() > 0);
	}
	
	@Test
	public void testLinkCustomer() {
		Customer customer = create();
		customerRepository.save(customer);
		long id = customer.getId();
				
		service.addCustomer(3L, customer);		
		Assert.assertEquals(customer.getId(), id);
	}
	
	
	
	private Customer create() {
		Customer customer = new Customer();
		customer.setFirstName("Mohammad");
		customer.setLastName("Shalabi");
		
		return customer;
	}
}
