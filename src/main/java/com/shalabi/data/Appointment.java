package com.shalabi.data;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="audiologist_id")
	private Audiologist audiologist;
	
	private int rate;
	
	@Column(name="appointment_date")
	private Date appointmentDate;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentsDate) {
		this.appointmentDate = appointmentsDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Audiologist getAudiologist() {
		return audiologist;
	}
	public void setAudiologist(Audiologist audiologist) {
		this.audiologist = audiologist;
	}
		
}
