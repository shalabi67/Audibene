package com.shalabi.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.shalabi.AudibeneApplication;
import com.shalabi.data.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AudibeneApplication.class)
//@WebAppConfiguration
public class AudiologistsControllerTests {
	@Autowired
	private AudiologistsController controller;
	private static final long audiologistId = 1L;
	
	@Test
	public void testCreateCustomer() {
		ResponseEntity<Void> result = controller.createCustomer(new Customer("first", "last"),  audiologistId);		
		Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}
	
	
	@Test
	public void testInvalidCustomer() {
		for(Customer customer : customers) {				
			ResponseEntity<Void> result = controller.createCustomer(customer, audiologistId);	
			Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		}
	}
	
	@Test
	public void testInvalidAudiologist() {
		ResponseEntity<Void> result = controller.createCustomer( new Customer("Mohammad", "Shalabi"), -1L);	
		Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
	}
	private Customer[] customers = {new Customer(null, "Shalabi"), new Customer("Mohammad", null)};
}
