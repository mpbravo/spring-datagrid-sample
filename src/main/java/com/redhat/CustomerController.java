package com.redhat;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class CustomerController {
    Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustomerRepositorySpringCache repository;
	
    @RequestMapping(method=RequestMethod.GET,value="/customer/{id}")
    @ApiOperation(
            value = "Get Customer",
            notes = "Get a Customer by id")
    public Customer getCustomer(
    		@PathVariable(value="id") String id
    		) {
    	Customer c = repository.findById(id);
    	
    	if (c ==null){
    		throw new CustomerNotFoundException();
    	}
    	
        return c;
    }

    @RequestMapping(method=RequestMethod.PUT,value="/customer")
    @ApiOperation(
            value = "Create a Customer",
            notes = "Create a Customer serialized in JSON")
    public Customer putCustomer(
    		@ApiParam(value = "Customer object", required = true, name = "Customer") @RequestBody Customer c
    		) {
    	
    	c.setId(UUID.randomUUID().toString());
    	
        return repository.insert(c.getId(), c);
    }

    @RequestMapping(method=RequestMethod.DELETE,value="/customer/{id}")
    @ApiOperation(
            value = "Delete a Customer",
            notes = "Delete a Customer by id")
     public void deleteCustomer(@RequestParam(value="id") String id
    		) {
    	repository.delete(id);
    }

    
    @RequestMapping(method=RequestMethod.POST,value="/customer/{id}")
    @ApiOperation(
            value = "Update a Customer",
            notes = "Update a Customer by id")
    public Customer updateCustomer(
    		@PathVariable(value="id") String id,
    		@ApiParam(value = "Customer object", required = true, name = "Customer") @RequestBody Customer c
    		) {
    	Customer c_cache = repository.findById(id);
    	
    	if (c_cache == null){
    		throw new CustomerNotFoundException();
       	}
    		
        Customer c_update = repository.insert(id, c);
        
    	return c_update;
    }
    
    
    @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="This customer is not found in the system")
    public class CustomerNotFoundException extends RuntimeException 
    {
    	private static final long serialVersionUID = 100L;
    }
    
}