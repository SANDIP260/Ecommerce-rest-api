package com.sm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sm.dto.OrderDTO;
import com.sm.entity.Orders;
import com.sm.service.IOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private IOrderService orderService;
	
	@PostMapping
	public ResponseEntity<Orders> placeOrder(@Valid @RequestBody OrderDTO dto)
	{
		Orders orders = orderService.placeOrder(dto);
		return new ResponseEntity<Orders>(orders,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrders()
	{
		List<Orders> allOrders = orderService.getAllOrders();
		return new ResponseEntity<List<Orders>>(allOrders,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Orders>> getAllOrdersByUserId(@PathVariable Long id)
	{
		List<Orders> allOrders = orderService.getAllOrdersByUserId(id);
		return new ResponseEntity<List<Orders>>(allOrders,HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long orderId)
	{
		String msg = orderService.cancelOrder(orderId);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

}
