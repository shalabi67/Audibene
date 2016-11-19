package com.shalabi.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonView;
import com.shalabi.View;
/**
 * 
 * @author mohammad
 *
 */
@Entity
@Table(name="Audiologists")
public class Audiologist {
	@JsonView(View.Summary.class)
	@Id
    private long id;	
	
	@JsonView(View.Summary.class)
    private String firstName;
	@JsonView(View.Summary.class)
    private String lastName;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="Audiologists_Customers",
    		joinColumns=@JoinColumn(name="audiologist_id"), 
    		inverseJoinColumns=@JoinColumn(name="customer_id"))
	private Set<Customer> customerList = new HashSet<Customer>(); 
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="audiologist")
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
    
    public Set<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(Set<Customer> customerList) {
		this.customerList = customerList;
	}
	
	public List<Appointment> getAppointments() {
		return appointments;
	}
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
}
