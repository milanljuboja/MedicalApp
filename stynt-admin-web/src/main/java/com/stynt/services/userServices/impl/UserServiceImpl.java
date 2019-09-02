package com.stynt.services.userServices.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stynt.dto.UserDTO;
import com.stynt.entities.userEntities.Role;
import com.stynt.entities.userEntities.User;
import com.stynt.exceptions.InternalServerErrorException;
import com.stynt.exceptions.ResourceNotFoundException;
import com.stynt.repositories.userRepositories.RoleRepository;
import com.stynt.repositories.userRepositories.UserRepository;
import com.stynt.services.userServices.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User saveUser(UserDTO userDTO) throws InternalServerErrorException, ResourceNotFoundException {
		
		for(User u : userRepository.findAll()) {
			if(userDTO.getUsername().equals(u.getUsername())) {
				throw new InternalServerErrorException("Username -> " + u.getUsername() + " already exists.");
			}
		}
		
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		List<Role> listOfRoles = new ArrayList<>();
		for(Role r : userDTO.getRoles()) {
			listOfRoles.add(roleRepository.findByRoleType(r.getRoleType())
					.orElseThrow(() -> new ResourceNotFoundException("Role -> " + r.getRoleType() + " not found.")));
		}
		user.setRoles(listOfRoles);
		return userRepository.save(user);
	}
	
	@Override
	public Page<User> findPaginated(Pageable pageable) {
		List<User> users = userRepository.findAll(); 
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<User> list;
 
        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, users.size());
            list = users.subList(startItem, toIndex);
        }
 
        Page<User> userPage = new PageImpl<User>(list, PageRequest.of(currentPage, pageSize), users.size());
 
        return userPage;
    }

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
}
