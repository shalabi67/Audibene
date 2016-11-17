package com.shalabi.rest;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.shalabi.data.Audiologist;
import com.shalabi.data.Customer;
import com.shalabi.data.IAudiologistRepository;
import com.shalabi.exceptions.MissingDataException;
import com.shalabi.exceptions.NotFoundException;
import com.shalabi.services.AudiologistService;

/**
 * This class will provide a rest APIs for customers. it will provide the following functionality:
 * create a customer.
 * get customer appointments.
 *
 */
@RestController
@RequestMapping("/audiologists")
public class AudiologistsController {
	@Autowired
	IAudiologistRepository audiologistRepository;
	
	@Autowired
	AudiologistService audiologistService;
	
	@GetMapping()
    public ResponseEntity<List<Audiologist>> getAudiologists() {
		List<Audiologist> audiologists = Lists.newArrayList(audiologistRepository.findAll());
		return new ResponseEntity<List<Audiologist>>(audiologists, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Audiologist> getAudiologist(@PathVariable("id") @NotNull  Long id) {
		Audiologist audiologist = audiologistRepository.findOne(id);		
		if (audiologist == null) {
            return new ResponseEntity<Audiologist>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Audiologist>(audiologist, HttpStatus.OK);
    }
	
    @RequestMapping(value ="/{audiologistId}/customers", method= RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Customer customer,  @PathVariable Long audiologistId) {
    	try {
    		audiologistService.addCustomer(audiologistId, customer);
    	}catch(NotFoundException e) {
    		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    	}catch(MissingDataException e1) {
    		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    	}
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
