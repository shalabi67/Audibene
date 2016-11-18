package com.shalabi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalabi.data.Audiologist;
import com.shalabi.data.Customer;
import com.shalabi.data.IAudiologistRepository;
import com.shalabi.data.ICustomerRepository;
import com.shalabi.exceptions.MissingDataException;
import com.shalabi.exceptions.NotFoundException;

@Service
public class AudiologistService {
	@Autowired
	IAudiologistRepository audiologistRepository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	public void createCustomer(long audiologistId, Customer customer) {
		//TODO: this method should return the created customer.
		if(customer.getFirstName() == null || customer.getLastName() == null) {
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

}
