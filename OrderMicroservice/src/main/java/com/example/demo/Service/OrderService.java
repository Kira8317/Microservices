package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.repo.Order;
import com.example.demo.repo.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;
	  public String placeOrder(Long productId, int quantity) { 
			String productServiceUrl = "http://PRODUCT/Product/check/" + productId + "/" +quantity;
			String response = restTemplate.getForObject(productServiceUrl, String.class);
		  
			  Order order = new Order();
			  order.setProductId(productId);
			  order.setQuantity(quantity);
			  order.setStatus(response);
			  orderRepository.save(order);
		  
		  return response;
	  }
	  public List<Order> getOrder(){
		  return orderRepository.findAll();
	  }
	  public String cancelOrder(Long orderId) {
		  	Optional<Order> orderOpt = orderRepository.findById(orderId);
		    if (orderOpt.isPresent()) {
		        Order order = orderOpt.get();
		        String productServiceUrl = "http://PRODUCT/product/restore/" + order.getProductId() + "/" + order.getQuantity();
		        restTemplate.getForObject(productServiceUrl, String.class);
		        orderRepository.deleteById(orderId);

		        return "Order cancelled and quantity restored.";
		    } 
		    else {
		        return "Order not found.";
		    }
		}
	 

}
