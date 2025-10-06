package com.example.demo.ProductController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ProductRepo.Product;
import com.example.demo.ProductService.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/Product")
public class ProductController {
	@Autowired
	ProductService ps;
	
	
	@Operation(summary = "Add a new product", description = "Creates and saves a new product in the database")
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product prod) {
		ps.addProduct(prod);
		return "Added Product";
	}
	
	@Operation(summary = "Get all products", description = "Retrieves all products from the database")
	@GetMapping()
		public List<Product> seeProduct() {
			return ps.getProduct();	
		}
	
	@Operation(summary = "Find product by ID", description = "Fetches a product using its ID")
	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id){
		return ps.findById(id);
	}
	@Operation(summary = "Delete all products", description = "Deletes all product records from the database")
	@DeleteMapping()
		public String deleteProduct(){
			ps.deleteProduct();
			return "Deleted all";
	}
	
	@Operation(summary = "Delete product by ID", description = "Deletes a specific product by its ID")
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		ps.deleteById(id);
	}
	
	@Operation(summary = "Update product price", description = "Updates the price of a product using its ID")
	@PutMapping("/{id}")
	public void updateById(@PathVariable Long id,@RequestParam int price) {
		ps.updateById(id, price);
	}
	
	@Operation(summary = "Check and update quantity", description = "Checks if the quantity is available and updates it accordingly")
	@GetMapping("/check/{id}/{quantity}")
	    public String checkAndUpdateQuantity(@PathVariable Long id, @PathVariable int quantity) {
	        return ps.checkAndUpdateQuantity(id, quantity);
	}
	
	@Operation(summary = "Restore product quantity", description = "Restores the quantity of a product by the given amount")
	@GetMapping("/restore/{id}/{quantity}")
	public String restoreQuantity(@PathVariable Long id, @PathVariable int quantity) {
	    return ps.restoreQuantity(id, quantity);
	}
}
