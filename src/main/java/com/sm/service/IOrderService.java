package com.sm.service;

import java.util.List;

import com.sm.dto.OrderDTO;
import com.sm.entity.Orders;



public interface IOrderService {
	
	public Orders placeOrder(OrderDTO dot);
	
	public List<Orders>  getAllOrders();
	
	public List<Orders> getAllOrdersByUserId(Long id);
	
	public String cancelOrder(Long orderId);
	

}
