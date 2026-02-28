package com.sm.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.dto.OrderDTO;
import com.sm.entity.Orders;
import com.sm.entity.Product;
import com.sm.entity.User;
import com.sm.globalException.ResourceNotFoundException;
import com.sm.globalException.StockException;
import com.sm.repository.IOrderRepository;
import com.sm.repository.IProductRepository;
import com.sm.repository.IUserRepository;

@Service
public class OrderServiceImple implements IOrderService {

	@Autowired
	private IOrderRepository orderRepo;
	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private IProductRepository prodRepo;

	@Override
	public Orders placeOrder(OrderDTO dto) {

		// check user is there or not
		User user = userRepo.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User is not found"));

		// check product is available or not
		Product product = prodRepo.findById(dto.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		// check stock is avalible or not
		if (product.getQuantity() < dto.getQty()) {
			throw new StockException("Insufficient stock");
		}

		// reduce the stock
		product.setQuantity(product.getQuantity() - dto.getQty());
		prodRepo.save(product);

		// price calculate
		Double totalPrice = product.getPrice() * dto.getQty();

		// place the new order
		Orders order = new Orders();
		order.setUser(user);
		order.setProduct(product);
		order.setOrderQty(dto.getQty());
		order.setTotalPrice(totalPrice);

		return orderRepo.save(order);
	}

	@Override
	public List<Orders> getAllOrders() {

		List<Orders> listOfOrders = orderRepo.findAll();

		return listOfOrders;
	}

	@Override
	public List<Orders> getAllOrdersByUserId(Long id) {

		List<Orders> orders = orderRepo.findByUserId(id);

		return orders;
	}

	@Override
	public String cancelOrder(Long orderId) {
		Orders order = orderRepo.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));

		// restore the stock
		Product product = order.getProduct();
		product.setQuantity(product.getQuantity() + order.getOrderQty());
		prodRepo.save(product);

		orderRepo.delete(order);

		return "Order cancelled successfully..";
	}

}
