package com.redhat;

import java.io.Serializable;

//import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
@ApiModel(value = "Customer")
@JsonPropertyOrder({"id","first-name","last-name","email"})
//@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Customer implements Serializable {

	@ApiModelProperty(value="Id", readOnly=true, position=1)
	@JsonProperty("id")
	private String id;
	
	@ApiModelProperty(value="First Name", position=2)
	@JsonProperty("first-name")
	private String firstName;
	
	@ApiModelProperty(value="Last Name", position=3)
	@JsonProperty("last-name")
    private String lastName;
	
	@ApiModelProperty(value="Email", position=4)
	@JsonProperty("email")
    private String email;

	public Customer(){
	}
    
	public String getId() {
        return id;
    }

	public void setId(String id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    	
}