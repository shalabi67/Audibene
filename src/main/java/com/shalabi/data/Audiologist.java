package com.shalabi.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
/**
 * 
 * @author mohammad
 *
 */
@Entity
@Table(name="Audiologists")
public class Audiologist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;	
	
    private String firstName;
    private String lastName;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="Audiologists_Customers",
    		joinColumns=@JoinColumn(name="audiologist_id"), 
    		inverseJoinColumns=@JoinColumn(name="customer_id"))
	private Set<Customer> customerList = new HashSet<Customer>(); 
	
	
    
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
}
