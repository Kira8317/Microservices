package com.example.demo.ProductController;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping("/Product")
public class ProductController {
	@Autowired
	ProductService ps;
	
	@PostMapping("/addProduct")
	public String addProduct(@RequestBody Product prod) {
		ps.addProduct(prod);
		return "Added Product";
	}
	@GetMapping()
		public List<Product> seeProduct() {
			return ps.getProduct();	
		}
	@GetMapping("/{id}")
	public Optional<Product>findById(@PathVariable Long id){
		return ps.findById(id);
	}
	@DeleteMapping()
		public String deleteProduct(){
			ps.deleteProduct();
			return "Deleted all";
}
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		ps.deleteById(id);
	}
	@PutMapping("/{id}")
	public void updateById(@PathVariable Long id,@RequestParam int price) {
		ps.updateById(id, price);
	}

@GetMapping("/check/{id}/{quantity}")
    public String checkAndUpdateQuantity(@PathVariable Long id, @PathVariable int quantity) {
        return ps.checkAndUpdateQuantity(id, quantity);
    }

@GetMapping("/restore/{id}/{quantity}")
public String restoreQuantity(@PathVariable Long id, @PathVariable int quantity) {
    return ps.restoreQuantity(id, quantity);
}


	

}
