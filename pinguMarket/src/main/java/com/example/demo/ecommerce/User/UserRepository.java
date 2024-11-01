package com.example.demo.ecommerce.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.User;



public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findById(String username);
	Optional<User> findByUserId(Integer userId);
	
	@Query(value = "SELECT * FROM user WHERE id = :id" , nativeQuery = true)
	User searchUser(@Param("id") Integer userId);
	
}