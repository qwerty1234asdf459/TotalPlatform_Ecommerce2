package com.example.demo.lms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LmsAdminUserRepository extends JpaRepository<LmsUser, Integer> {

}
