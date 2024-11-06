package com.example.demo.ecommerce.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.ecommerce.Entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	/* 파일명으로 파일ID 조회 */
	@Query(value = "SELECT image_id FROM image WHERE image_name = :fileName", nativeQuery = true)
	Integer findFileIdByFileName(@Param("fileName") String fileName);
}
