package com.shalabi.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.shalabi.View;

/**
 *  This class identifies Customer attributes.
 *  
 *  @author mohammad
 */
@Entity
@Table(name="Customers")
public class Customer {
	@JsonView(View.Summary.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@JsonView(View.Summary.class)
    private String firstName;
	@JsonView(View.Summary.class)
    private String lastName;
    
    public Customer() {
    	
    }
    public Customer(String firstName, String lastName) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    }
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="customerList")
	private Set<Audiologist> audiologistList = new HashSet<Audiologist>();
    
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="customer")
    private List<Appointment> appointments = new ArrayList<Appointment>();

    
	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Set<Audiologist> getAudiologistList() {
		return audiologistList;
	}
	public void setAudiologistList(Set<Audiologist> audiologistList) {
		this.audiologistList = audiologistList;
	}
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
}
