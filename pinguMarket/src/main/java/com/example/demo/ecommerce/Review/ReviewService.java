package com.example.demo.ecommerce.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.Review;
import com.example.demo.ecommerce.Entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewService {
	
	private final ReviewRepository rer;
	
	public Review getReview(Integer id) throws CanNotFoundException {
	    return this.rer.findById(id)
	                   .orElseThrow(() -> new CanNotFoundException("리뷰를 찾을 수 없습니다."));
	}
	
	public void reviewCreate(Product p, User u,  String scope, String title, String contents) {
		Review r = new Review();
		r.setProduct(p);
		r.setUser(u);
		r.setScope(scope);
		r.setTitle(title);
		r.setContents(contents);
		r.setUpdateDate(LocalDateTime.now());
		this.rer.save(r);
	}
	
	public void reviewModify(Review r, String scope, String title, String contents) {
		r.setScope(scope);
		r.setTitle(title);
		r.setContents(contents);
		r.setUpdateDate(LocalDateTime.now());
		this.rer.save(r);
	}

	public void delete(Review r) {
		this.rer.delete(r);
		
	}
	
	



}
