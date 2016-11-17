package com.shalabi.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shalabi.AudibeneApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AudibeneApplication.class)
//@WebAppConfiguration
public class CustomersControllerTests {
	@Autowired
	private CustomersController controller;
	
	@Test
	public void testGetCustomer() {
		controller.getCustomer(1L);
	}

}
