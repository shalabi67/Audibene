package com.shalabi.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;



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
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date());
		audiologistService.createAppointment(audiologistId, customer.getId(), appointment);
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		List<Appointment> list = appointmentRepository.findByAudiologist_id(audiologistId);
		Assert.assertEquals(1, list.size());
		
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
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(new Date());
		audiologistService.createAppointment(audiologistId, customer.getId(), appointment);
		audiologistService.createAppointment(nextAudiologistId, customer.getId(), appointment);
		
		appointment.setAppointmentDate(start);
		audiologistService.createAppointment(audiologistId, customer.getId(), appointment);
		
		appointment.setAppointmentDate(end);
		audiologistService.createAppointment(audiologistId, customer.getId(), appointment);
		
		appointment.setAppointmentDate(cal.getTime());
		audiologistService.createAppointment(audiologistId, customer.getId(), appointment);
		
		
		List<Appointment> list = appointmentRepository.getAudiologistAppointmentsBetweenDates(audiologistId, start, end);
		Assert.assertEquals(2, list.size());
		
	}

}
