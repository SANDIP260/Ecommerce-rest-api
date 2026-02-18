package com.sm.service;

import java.util.List;
import java.util.Optional;

import com.sm.dto.ProductDTO;
import com.sm.entity.Product;

public interface IProductService {
	
	
	String addProuduct(ProductDTO proddto);
	List<Product> getAllProducts();
	Product getProductById(Long id);
	 String updateProduct(Long id, ProductDTO dto);
	 String partialProductUpdate(Long id, ProductDTO dto);
	 String deleteProduct(Long id);
	
}
