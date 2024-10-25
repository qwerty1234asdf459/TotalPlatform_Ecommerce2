package com.example.demo.ecommerce.CsQuestion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.User;


public interface CsQuestionUserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findById(String id);

}
