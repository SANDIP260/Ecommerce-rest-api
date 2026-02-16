package com.sm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
