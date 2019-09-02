package com.stynt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stynt.dto.UserDTO;
import com.stynt.entities.userEntities.User;
import com.stynt.exceptions.InternalServerErrorException;
import com.stynt.exceptions.ResourceNotFoundException;
import com.stynt.services.userServices.UserService;

/**
 * Class used for managing users via REST
 * @author Milan Ljuboja
 */
@RestController
@RequestMapping("/userCrud")
public class UserManipulationController {
	
	private UserService userService;
	
	@Autowired
	public UserManipulationController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Used to create user via REST. Example body:
	 * {
		"firstName": "Milan",
		"lastName": "Ljuboja",
		"username": "milan.ljuboja",
		"password": "123",
		"roles": [{
			"roleType": "MODERATOR"
		},{
			"roleType": "ADMIN"
		}]
	   }
	 * @param userDTO
	 * @return
	 * @throws InternalServerErrorException
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("")
	public User saveUser(@RequestBody UserDTO userDTO) throws InternalServerErrorException, ResourceNotFoundException {
		return userService.saveUser(userDTO);
	}
}
