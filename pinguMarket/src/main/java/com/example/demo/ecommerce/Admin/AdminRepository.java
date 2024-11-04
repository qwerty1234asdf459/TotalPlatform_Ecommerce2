package com.example.demo.ecommerce.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Admin;



public interface AdminRepository extends JpaRepository<Admin, Integer>{
	Optional<Admin> findByAdCode(String adCode);
	Optional<Admin> findByAdminId(Integer adminId);
	
	@Query(value = "SELECT * FROM admin WHERE admin_id = :id" , nativeQuery = true)
	Admin searchAdminUser(@Param("id") Integer adminId);


}
