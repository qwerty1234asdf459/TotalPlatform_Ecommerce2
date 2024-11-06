package com.example.demo.ecommerce.PaymentDetail;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Cart;
import com.example.demo.ecommerce.Entity.Payment;
import com.example.demo.ecommerce.Entity.PaymentDetail;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Product.ProductRepository;
import com.example.demo.ecommerce.Review.CanNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaymentDetailService {

	private final PaymentDetailRepository paymentDetailRepository;
	private final ProductRepository productRepository;
	
	public PaymentDetail getPaymentDetail(Integer userId, Integer productId) throws CanNotFoundException {
		Optional<PaymentDetail> pd = this.paymentDetailRepository.findByUserProduct(userId, productId);
		if(pd.isPresent()) {
			return pd.get();
		}else {
			throw new CanNotFoundException("구매한 상품이 없습니다.");
		}
	}
	
	public void createPaymentDetail(Payment payment,Cart cart) {
		PaymentDetail pd = new PaymentDetail();
		Product p = cart.getProduct();
		
		pd.setPayment(payment);
		pd.setCount(cart.getProductCount());
		pd.setPrice(cart.getProduct().getPrice());
		pd.setProduct(cart.getProduct());
		
		p.setAmount(p.getAmount()-cart.getProductCount());
		
		this.paymentDetailRepository.save(pd);
		this.productRepository.save(p);
	}
}
