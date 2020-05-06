package com.cts.om.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.om.entities.Order;
import com.cts.om.entities.OrderItem;
import com.cts.om.entities.Product;
import com.cts.om.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repo;
	@Autowired
	private ProductServiceProxy psp;
	public List<Order> getorders(){
	
		List<Order> orders=(List<Order>) repo.findAll();
		for(Order o:orders) {
			for(OrderItem om:o.getOrderItem()) {
				Product product=psp.getProductById(om.getProductId());
				om.setProduct(product);
			}
		}
		return orders;
	}
	
	public void addOrder(Order order) {
		repo.save(order);
	}
	
	public void deleteOrder(Long orderId) {
		repo.deleteById(orderId);
	}
	
	public void updateOrder(Order order) {
		repo.save(order);
	}

}
