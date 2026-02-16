package com.sm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sm.entity.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Long> {

}
