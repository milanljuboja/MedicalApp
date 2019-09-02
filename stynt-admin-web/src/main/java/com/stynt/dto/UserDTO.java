package com.stynt.dto;

import java.util.List;

import com.stynt.entities.userEntities.Role;

public class UserDTO {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private List<Role> roles;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<Role> getRoles() {
		return roles;
	}

}
