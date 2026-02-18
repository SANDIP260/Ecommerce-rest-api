package com.sm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.dto.ProductDTO;
import com.sm.entity.Product;
import com.sm.service.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private IProductService prodService;
	
	@PostMapping("/product")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDTO  dto)
	{
		String msg = prodService.addProuduct(dto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		List<Product> allProducts = prodService.getAllProducts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id)
	{
		Product product = prodService.getProductById(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto)
	{
		
		String msg = prodService.updateProduct(id, dto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/product/{id}")
	public ResponseEntity<String> partialProductUpdate(@PathVariable Long id, @RequestBody ProductDTO dto)
	{
		
		String msg = prodService.partialProductUpdate(id, dto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id)
	{
		String msg = prodService.deleteProduct(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	

}
