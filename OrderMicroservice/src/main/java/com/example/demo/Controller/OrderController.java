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

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Service.OrderService;
import com.example.demo.repo.Order;
import com.example.demo.repo.OrderRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing orders.
 */
@RestController
@RequestMapping("/order")
@Tag(name = "Order Controller", description = "Handles placing, canceling, and retrieving orders")
public class OrderController {	

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderrepository;

    /**
     * Places an order for a given product and quantity.
     *
     * @param productId ID of the product to order
     * @param quantity quantity of the product
     * @return confirmation message
     */
    @PostMapping("/place")
    @Operation(summary = "Place an order", description = "Places an order for a product with specified quantity")
    public String placeOrder(@RequestParam Long productId, @RequestParam int quantity) {
        return orderService.placeOrder(productId, quantity);
    }

    /**
     * Cancels an order by its ID.
     *
     * @param orderId ID of the order to cancel
     * @return cancellation confirmation
     */
    @DeleteMapping("/cancel/{orderId}")
    @Operation(summary = "Cancel an order", description = "Cancels an existing order by its ID")
    public String cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return list of orders
     */
    @GetMapping()
    @Operation(summary = "Get all orders", description = "Retrieves a list of all placed orders")
    public List<Order> getall() {
        return orderrepository.findAll();
    }
    
    /*
     * Retrieves order using the id 
     * @Param orderId of the order
     */
    @GetMapping("/{id}")
    @Operation(summary="Get your order using Id",description="Retriveves a single order entry using Id")
    public Order getOrderById( @PathVariable Long id ){
    	return orderService.getOrderById(id)
    			.orElseThrow(()-> new ResourceNotFoundException("Order with given Id "+id+" not found"));
    	
    }
}
