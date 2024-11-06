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
//	사용자의 id가 있는 모든 리뷰를 조회
	
	public Review getReview(Integer userId, Integer productId) throws CanNotFoundException {
		Optional<Review> review = this.rer.findByUserProduct(userId, productId);
		if(!review.isPresent()) {
			return null;
		}else {
			throw new CanNotFoundException("이미 리뷰가 있습니다.");
		}
	}
//	사용자가 해당 상품을 구매했는지 검사
	
	public Review getReviewModify(Integer userId, Integer reviewId) throws CanNotFoundException {
		Optional<Review> review = this.rer.findByUserReview(userId, reviewId);
		if(review.isPresent()) {
			return review.get();
		}else {
			throw new CanNotFoundException("작성한 리뷰가 없습니다.");
		}
	}
//	사용자가 리뷰를 수정할 때 그 리뷰를 작성했는지 검사
	
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
//	리뷰 생성 매서드
	
	public void reviewModify(Review r, String scope, String title, String contents) {
		r.setScope(scope);
		r.setTitle(title);
		r.setContents(contents);
		r.setUpdateDate(LocalDateTime.now());
		this.rer.save(r);
	}
//	리뷰 수정 메서드

	public void delete(Review r) {
		this.rer.delete(r);
	}
	
	



}
