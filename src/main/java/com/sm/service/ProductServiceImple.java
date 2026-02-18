package com.sm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.dto.ProductDTO;
import com.sm.entity.Product;
import com.sm.globalException.ResourceNotFoundException;
import com.sm.repository.IProductRepository;

@Service
public class ProductServiceImple implements IProductService {
	
	@Autowired
	private IProductRepository prodRepo;

	@Override
	public String addProuduct(ProductDTO proddto) {
		
		
		Product product = new Product();
		product.setName(proddto.getName());
		product.setPrice(proddto.getPrice());
		product.setQuantity(proddto.getQuantity());
		
		Long id = prodRepo.save(product).getId();
		return "product added successfully  with id :: "+id;
	}
	
	@Override
	public List<Product> getAllProducts() {
		
		List<Product> listOfProduct = prodRepo.findAll();
		
		if(listOfProduct.isEmpty())
		{
			throw new ResourceNotFoundException("No Product is available...");
		}
		
		return listOfProduct;
	}
	
	@Override
	public Product getProductById(Long id) {
		
		Product product = prodRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product is not avaliable for this id :: "+id));
		
		return product;
	}
	
	@Override
	public String updateProduct(Long id, ProductDTO dto) {

	    Product product = prodRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Product not found with id: " + id));

	    product.setName(dto.getName());
	    product.setPrice(dto.getPrice());
	    product.setQuantity(dto.getQuantity());

	    return "product details updated sucessfully with id "+prodRepo.save(product).getId();
	}
	
	@Override
	public String partialProductUpdate(Long id, ProductDTO dto) {

	    Product product = prodRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Product not found with id: " + id));

	    if (dto.getName() != null) {
	        product.setName(dto.getName());
	    }

	    if (dto.getPrice() >=0) {
	        product.setPrice(dto.getPrice());
	    }

	    if (dto.getQuantity() >=0) {
	        product.setQuantity(dto.getQuantity());
	    }

	    prodRepo.save(product);

	    return "Product updated successfully with id " + product.getId();
	}
	
	@Override
	public String deleteProduct(Long id) {

	    if (!prodRepo.existsById(id)) {
	        throw new ResourceNotFoundException(
	                "Product not found with id: " + id);
	    }

	    prodRepo.deleteById(id);

	    return "Product deleted successfully with id " + id;
	}



}
