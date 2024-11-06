package com.example.demo.ecommerce.Cart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional  //  예외 발생 시 롤백, 정상 종료 시 커밋 등의 필요한 코드를 삽입
public class CartService {
	
	private final CartRepository car;
	
	public void createCart(User user, Product product, Integer count) {
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setUpdateDate(LocalDateTime.now());
		cart.setProductCount(count);
		this.car.save(cart);
	}
	
	public Cart getCart(Integer cartId) throws Exception {
		Optional<Cart> cart = this.car.findById(cartId);
		if(cart.isPresent()) {
			return cart.get();
		} else {
			throw new nosignException("존재하지 않는 카트입니다.");
		}
	}
	
	public Cart getCart(Integer ui, Integer pi) throws Exception {
		Optional<Cart> cart = this.car.findByUserProduct(ui, pi);
		if(cart.isPresent()) {
			return cart.get();
		} else {
			throw new nosignException("존재하지 않는 카트입니다.");
		}
	}
	
	///////////////// 카트에 이미 있는 제품인지 확인하는 메소드///////////
	public boolean cartOverlappingCheck(Product product, User user) {
		boolean flag = true;
		List<Cart> cartList = user.getCartList();
		for(Cart cart:cartList) {
			if(cart.getProduct().equals(product)) {
				flag = false;
			}
		}
		return flag;
	}
	
	
	////////////////////////카트에 담긴 제품 수량 변경//////////////////////////////
	public void modifyCart(Cart cart, Integer count) {
		cart.setProductCount(count);
		this.car.save(cart);
	}
	
	//////////////////////////////cart의 Id 리스트를 Cart의 엔티티 리스트로 바꾸는 메소드///////////////////////////
	public List<Cart> getCartByList(List<String> cartIdList) throws Exception {
		List<Cart> cartList = new ArrayList<Cart>();
		
		for(int i = 0; i < cartIdList.size(); i++) {
			cartList.add(this.getCart(Integer.parseInt(cartIdList.get(i))));
		}
		
		return cartList;
	}
	
	
	
	public void delete(Cart cart) {
		this.car.delete(cart);
	}

}
