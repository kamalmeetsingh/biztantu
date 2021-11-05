package com.biztantu.api.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public Product getProduct(Long id) throws ProductException {
		Optional<Product> product = repository.findById(id);
		if(product.isPresent()) {
            return product.get();
        } else {
            throw new ProductException("No record exist for given id");
        }
	}
	
	public List<Product> getProductList() throws ProductException {
		return repository.findAll();
	}
	
	public Product addProduct(Product product) {
		Product createdProduct = repository.save(product);
		return createdProduct;
	}

}
