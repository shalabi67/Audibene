package com.shalabi.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shalabi.data.Appointment;
import com.shalabi.data.Audiologist;
import com.shalabi.data.Customer;
import com.shalabi.data.IAppointmentRepository;
import com.shalabi.data.IAudiologistRepository;
import com.shalabi.data.ICustomerRepository;
import com.shalabi.exceptions.MissingDataException;
import com.shalabi.exceptions.NotFoundException;

@Service
public class AppointmentService {
	@Autowired
	IAudiologistRepository audiologistRepository;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	@Autowired
	IAppointmentRepository appointmentRepository;
	
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
	
	public List<Appointment> getAudiologistNextWeekAppointments(Long audiologistId) {
		Calendar cal = getNextWeek();  
		Date start = cal.getTime();
		
		cal.add(Calendar.DATE, 7);
		Date end = cal.getTime();
			
		return appointmentRepository.getAudiologistAppointmentsBetweenDates(audiologistId, start, end);
	}

	public Calendar getNextWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); 
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		
		return cal;
	}
	
	public Appointment getCustomerNextAppointment(long customerId) {
		Customer customer = customerRepository.findOne(customerId);
		if(customer == null) {
			throw new NotFoundException(customerId + " not found");
		}
		
		Date current = new Date();
		
		List<Appointment> list = appointmentRepository.findByCustomer_idAndAppointmentDateGreaterThanOrderByAppointmentDate(customerId, current);
		if(list.size() == 0) {
			throw new NotFoundException(customerId + "  has no appointments.");
		}
		return list.get(0);
		
	}

}
