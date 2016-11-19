package com.shalabi.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.shalabi.View;
import com.shalabi.data.Appointment;
import com.shalabi.data.Customer;
import com.shalabi.data.IAppointmentRepository;
import com.shalabi.data.ICustomerRepository;
import com.shalabi.exceptions.NotFoundException;
import com.shalabi.services.AppointmentService;
import com.shalabi.services.AudiologistService;

/**
 * This class will provide a rest APIs for customers. it will provide the following functionality:
 * create a customer.
 * get customer appointments.
 *
 */
@RestController
@RequestMapping("/customers")
public class CustomersController {
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	AudiologistService audiologistService;
	
	@Autowired
	IAppointmentRepository customerIdRepository;
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping()
    public ResponseEntity<List<Customer>> getAudiologists() {
		List<Customer> customers = Lists.newArrayList(customerRepository.findAll());
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Customer> getAudiologist(@PathVariable("id") @NotNull  Long id) {
		Customer customer = customerRepository.findOne(id);		
		if (customer == null) {
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
	
        
    @JsonView(View.Summary.class)
    @GetMapping("/{customerId}/appointments/next")
    public ResponseEntity<Appointment> getNextWeekAppointments(@PathVariable Long customerId) {
    	try {
	    	Appointment appointment = appointmentService.getCustomerNextAppointment(customerId);
	    	return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
    	}catch(NotFoundException e) {
    		return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
    	}
    }
}
