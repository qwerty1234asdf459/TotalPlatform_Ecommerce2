package com.example.demo.ecommerce.Product;


import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Admin.Product.AdminProductRepository;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import javassist.NotFoundException;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	  private final ProductRepository pr;
    private final AdminProductRepository apr;
	
	public List<Product> getListProduct(List<String>productIdList){
		return this.pr.findByProductIdIn(productIdList);
	}

	public Product getProduct(Integer productId) throws CanNotFoundException{
		Optional<Product> p1 = this.pr.findById(productId);
		if(p1.isPresent()) {
			return p1.get();
		} else {
			throw new CanNotFoundException("데이터를 찾을 수 없습니다.");
		}
	}
  
	

	
//---------------------	id값으로 퀴즈 데이터 조회---------------------  
//    public Product getProduct(Integer productId) { 
//        Optional<Product> q = this.apr.findById(productId);
//        return q.get();
//        if (q.isPresent()) {
//            return q.get();
//        } else {
//            throw new DataNotFoundException("quiz not found");
//            //만약 id값에 해당하는 질문 데이터가 없다면 DataNotFoundException 실행
//        }
//    }

	
	
// 	private final ProductRepository pr;
	
// 	public Product getProduct(Integer productId) throws CanNotFoundException {
// 		Optional<Product> product = this.pr.findById(productId);
// 		if(product.isPresent()) {
// 			return product.get();
// 		}
// 		else {
// 			throw new CanNotFoundException("데이터를 찾을 수 없습니다.");
// 		}
// 	}
	
}
