package com.stynt.services.userServices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stynt.dto.UserDTO;
import com.stynt.entities.userEntities.User;
import com.stynt.exceptions.InternalServerErrorException;
import com.stynt.exceptions.ResourceNotFoundException;

public interface UserService {

	public List<User> findAllUsers();
	public User saveUser(UserDTO userDTO) throws InternalServerErrorException, ResourceNotFoundException;
	public Page<User> findPaginated(Pageable pageable);
	public Page<User> findAll(Pageable pageable);
}
