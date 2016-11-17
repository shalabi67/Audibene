package com.shalabi.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *  This class identifies Customer attributes.
 *  
 *  @author mohammad
 */
@Entity
@Table(name="Customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	
    private String firstName;
    private String lastName;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="customerList")
	private Set<Audiologist> audiologistList = new HashSet<Audiologist>();

    
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
}
