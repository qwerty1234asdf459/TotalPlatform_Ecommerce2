package com.example.demo.ecommerce.productimg;


import org.springframework.stereotype.Service;

import com.example.demo.ecommerce.Entity.Image;
import com.example.demo.ecommerce.Entity.Product;
import com.example.demo.ecommerce.Entity.ProductImg;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductImgService {

	private final ProductImgRepository productImgRepository;
	
	public void create(Product product, Image image) {
		ProductImg pi = new ProductImg();
		
		pi.setProduct(product);
		pi.setImage(image);
		pi.setImgType(image.getImageType());
		
		this.productImgRepository.save(pi);
	}
}
