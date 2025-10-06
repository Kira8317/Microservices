package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.repo.Order;
import com.example.demo.repo.OrderRepository;

/**
 * Service class for managing order-related operations.
 * Provides methods for placing, retrieving, and canceling orders.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Places an order for a product by communicating with the Product service.
     * It checks the product's availability and updates its quantity through a REST API call.
     * If successful, creates a new order with status and saves it to the database.
     *
     * @param productId the ID of the product being ordered
     * @param quantity the quantity of the product to order
     * @return response message from the product service (e.g., "Order placed successfully", "Insufficient quantity")
     */
    public String placeOrder(Long productId, int quantity) {
        String productServiceUrl = "http://PRODUCT/Product/check/" + productId + "/" + quantity;
        String response = restTemplate.getForObject(productServiceUrl, String.class);

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus(response);
        orderRepository.save(order);

        return response;
        }

    /**
     * Retrieves all orders from the database.
     *
     * @return list of all orders
     */
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(Long id) {
    	return orderRepository.findById(id);
    }

    /**
     * Cancels an existing order.
     * If the order is found, it communicates with the Product service to restore the product quantity.
     * Then deletes the order from the database.
     *
     * @param orderId the ID of the order to cancel
     * @return a message indicating whether the cancellation was successful or if the order was not found
     */
    public String cancelOrder(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            String productServiceUrl = "http://PRODUCT/Product/restore/" + order.getProductId() + "/" + order.getQuantity();
            restTemplate.getForObject(productServiceUrl, String.class);
            orderRepository.deleteById(orderId);

            return "Order cancelled and quantity restored.";
        } else {
            return "Order not found.";
        }
    }
}
