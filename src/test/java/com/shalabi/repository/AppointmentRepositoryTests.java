package com.shalabi.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;



import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shalabi.AudibeneApplication;
import com.shalabi.data.Appointment;
import com.shalabi.data.Customer;
import com.shalabi.data.IAppointmentRepository;
import com.shalabi.data.ICustomerRepository;
import com.shalabi.services.AppointmentService;
import com.shalabi.services.AudiologistService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AudibeneApplication.class)
public class AppointmentRepositoryTests {
	@Autowired
	IAppointmentRepository appointmentRepository;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	AudiologistService audiologistService;
	
	@Autowired
	ICustomerRepository customerRepository;
	
	static final long audiologistId = 1L;
	static final long nextAudiologistId = 2L;
	
	@Test
	public void testGetAppointmentByAudiologist() {
		Customer customer = new Customer("Mohammad", "Shalabi");		
		customerRepository.save(customer);
		
		List<Appointment> list = appointmentRepository.findByAudiologist_id(audiologistId);
		int count = list.size();
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date());
		audiologistService.createAppointment(audiologistId, customer.getId(), appointment);
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		list = appointmentRepository.findByAudiologist_id(audiologistId);
		Assert.assertEquals(count + 1, list.size());
		
	}
	
	@Test
	public void testGetAudiologistAppointmentsBetweenDate() {
		Customer customer = new Customer("Mohammad", "Shalabi");		
		customerRepository.save(customer);
		
		Calendar cal = appointmentService.getNextWeek();
		Date start = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date end = cal.getTime();
		cal.add(Calendar.DATE, 1);
		
		List<Appointment> list = appointmentRepository.getAudiologistAppointmentsBetweenDates(nextAudiologistId, start, end);
		int count = list.size();
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date());
		audiologistService.createAppointment(3L, customer.getId(), appointment);
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		appointment.setAppointmentDate(start);
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		appointment.setAppointmentDate(end);
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		appointment.setAppointmentDate(cal.getTime());
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		
		list = appointmentRepository.getAudiologistAppointmentsBetweenDates(nextAudiologistId, start, end);
		Assert.assertEquals(count + 2, list.size());
		
	}
	
	@Test
	public void testCustomerAppointment() {
		Customer customer = new Customer("Mohammad", "Shalabi");		
		customerRepository.save(customer);		
		long customerId = customer.getId();
		
		Calendar calendar = Calendar.getInstance();		
		for(int i=-3; i<6; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, i);
			add(cal.getTime(), customerId);			
		}
		
		Date current = new Date();
		List<Appointment> appointments = appointmentRepository
				.findByCustomer_idAndAppointmentDateGreaterThanOrderByAppointmentDate(customerId, current);
		Appointment appointment = appointments.get(0);
		
		
		if(appointment.getAppointmentDate().after(current)) {
			calendar.add(Calendar.DATE, 2);
			Date twoDay = calendar.getTime();
			if(appointment.getAppointmentDate().before(twoDay)) {
				return;
			}			
		}
		Assert.fail();
	}
	
	private void add(Date date, Long customerId) {
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(date);
		audiologistService.createAppointment(audiologistId, customerId, appointment);
	}
}
