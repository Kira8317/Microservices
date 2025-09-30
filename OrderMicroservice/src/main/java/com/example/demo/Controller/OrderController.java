package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.OrderService;
import com.example.demo.repo.Order;
import com.example.demo.repo.OrderRepository;

@RestController
@RequestMapping("/order")

public class OrderController {
	@Autowired
    private OrderService orderService;
	
	@Autowired
	OrderRepository orderrepository;
	  @PostMapping("/place") 
	  public String placeOrder(@RequestParam Long productId, @RequestParam int quantity)
	  { 
		  return orderService.placeOrder(productId, quantity); 
	  }

	@DeleteMapping("/cancel/{orderId}")
	public String cancelOrder(@PathVariable Long orderId) {
	    return orderService.cancelOrder(orderId);
	}	

	@GetMapping()
	public List<Order> getall(){
		return orderrepository.findAll();
	}
  
 }


