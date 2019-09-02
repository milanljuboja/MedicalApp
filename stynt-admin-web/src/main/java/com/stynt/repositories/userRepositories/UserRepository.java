package com.stynt.repositories.userRepositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.stynt.entities.userEntities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User,Long> {

	public Optional<User> findByUsername(String username);
	public Page<User> findAll(Pageable pageable);
	public List<User> findAll();
	
}
