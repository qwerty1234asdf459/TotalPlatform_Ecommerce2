package com.example.demo.ecommerce.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.User;



public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findById(String username);
	Optional<User> findByUserId(Integer userId);
}