package com.example.demo.ecommerce.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.ecommerce.Entity.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer>{
	Optional<Admin> findByAdCode(String adCode);
	Optional<Admin> findByAdminId(Integer adminId);

}
