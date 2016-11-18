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
import com.shalabi.exceptions.MissingDataException;
import com.shalabi.exceptions.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AudibeneApplication.class)
public class AudiologistServiceTests {
	@Autowired
	AudiologistService service;
	
	@Autowired
	IAudiologistRepository repository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	static final long audiologistId = 1L;
	
	
	@Test
	public void testAddCustomer() {
		Customer customer = new Customer("Mohammad", "Shalabi");
		
		service.createCustomer(audiologistId, customer);		
	}
	
	@Test(expected=MissingDataException.class)
	public void testInvalidCustomer() {
		for(Customer customer : customers) {				
			service.createCustomer(audiologistId, customer);	
		}
		
		Assert.fail();
	}
	
	@Test(expected=NotFoundException.class)
	public void testInvalidAudiologist() {
		service.createCustomer(-1, new Customer("Mohammad", "Shalabi"));
		
		Assert.fail();
	}
	
	
	
	
	
	private Customer[] customers = {new Customer(null, "Shalabi"), new Customer("Mohammad", null)};
}
