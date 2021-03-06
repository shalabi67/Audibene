package com.shalabi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalabi.data.Appointment;
import com.shalabi.data.Audiologist;
import com.shalabi.data.Customer;
import com.shalabi.data.IAppointmentRepository;
import com.shalabi.data.IAudiologistRepository;
import com.shalabi.data.ICustomerRepository;
import com.shalabi.exceptions.InvalidDataException;
import com.shalabi.exceptions.MissingDataException;
import com.shalabi.exceptions.NotFoundException;

@Service
public class AudiologistService {
	@Autowired
	IAudiologistRepository audiologistRepository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IAppointmentRepository appointmentRepository;
	
	public void createCustomer(long audiologistId, Customer customer) {
		//TODO: this method should return the created customer.
		if(customer.getFirstName() == null || customer.getLastName() == null || 
				customer.getFirstName().length() == 0 || customer.getLastName().length() == 0) {
			throw new MissingDataException("Customer is missing first name or last name.");
		}
		
		Audiologist audiologist = audiologistRepository.findOne(audiologistId);
		if(audiologist == null) {
			throw new NotFoundException(audiologistId + " not found");
		}
		
		long id = customer.getId();		
		
		try {
			customer.setId(0L);  //support only creation no linking
			audiologist.getCustomerList().add(customer);
			audiologistRepository.save(audiologist);
			customer.setId(id);
		}catch(Exception e) {
			customer.setId(id);
		}
		
	}
	
	public void createAppointment(long audiologistId, long customerId, Appointment appointment) {
		//TODO: this method should return the created appointment.
		if(appointment.getAppointmentDate() == null) {
			throw new MissingDataException("Appointment is missing date.");
		}
		Audiologist audiologist = audiologistRepository.findOne(audiologistId);
		if(audiologist == null) {
			throw new NotFoundException(audiologistId + " audiologist not found");
		}
		Customer customer = customerRepository.findOne(customerId);
		if(customer == null) {
			throw new NotFoundException(customerId + " customer not found");
		}
		
		long count = audiologist.getCustomerList().stream().filter(p -> p.getId() == customerId).count();
		if(count == 0) {
			throw new InvalidDataException("Customer does not belong to audiologist.");
		}
		
		appointment.setAudiologist(audiologist);
		appointment.setCustomer(customer);
		
		audiologist.getAppointments().add(appointment);
		customer.getAppointments().add(appointment);
		
		audiologistRepository.save(audiologist);
		
	}

}
