package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	@Query("select users from User users where 2 member of users.authorities and users.id!=:id")
	List<User> getAllZirants(long id);
	
}
