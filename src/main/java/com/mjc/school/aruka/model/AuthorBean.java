package com.mjc.school.aruka.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class AuthorBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@NotBlank(message="must not be empty")
	private String firstName;
	@NotBlank
	private String lastName;
	public AuthorBean(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AuthorBean() {
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
	
	public String getName() {
		return firstName + " " + lastName;
	}
	
}
