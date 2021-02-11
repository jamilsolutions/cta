package com.fs.cta.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Customer")
public class CustomerDTO {

	@JacksonXmlProperty(localName = "id")
	private Long id;
	
	@JacksonXmlProperty(localName = "name")
	private String name;
	
	@JacksonXmlProperty(localName = "email")
	private String email;

	@JacksonXmlProperty(localName = "address")
	private String address;
	
	@JacksonXmlProperty(localName = "dateOfBirth")
	private String dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
