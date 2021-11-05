package com.biztantu.api.product;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biztantu.api.log.Logger;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	Logger logger = new Logger();
	
	@GetMapping(value="/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Product> getProduct(@PathVariable Long id) throws ProductException{
		logger.info("Initiated flow");
		Product product = productService.getProduct(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping(value="/products", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Product>> getProduct() throws ProductException{
		logger.info("Initiated flow");
		List<Product> product = productService.getProductList();
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PostMapping(path="/products", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addProduct(@RequestBody Product product){
		Product createProduct = productService.addProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createProduct.getProductId()).toUri();
		return ResponseEntity.created(location).build();
	}
	

}
