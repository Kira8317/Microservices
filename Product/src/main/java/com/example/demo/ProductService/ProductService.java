package com.example.demo.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ProductRepo.Product;
import com.example.demo.ProductRepo.ProductRepo;
@Service
public class ProductService {

	@Autowired
	ProductRepo productrepo;
	
	public void addProduct(Product pro ) {
		productrepo.save(pro);
	}
	
	public String deleteProduct() {
		productrepo.deleteAll();
		return "Deleted all";
	}
	
	public Optional<Product >findById(Long id) {
		return productrepo.findById(id);
	}
	
	public List<Product> getProduct(){
		return productrepo.findAll();
	}
	
	public void deleteById(Long id) {
		productrepo.deleteById(id);
	}
	
	public Product updateById(Long id,int updatedprice ) {
		return  productrepo.findById(id)
				.map(product -> {
					product.setPrice(updatedprice);
					return productrepo.save(product);
				})
				.orElse(null);
	}
	public String checkAndUpdateQuantity(Long productId, int requestedQuantity) {
	    Optional<Product> productOpt = productrepo.findById(productId);
	    
	    if (productOpt.isPresent()) {
	        Product product = productOpt.get();
	        if (product.getQuantity() >= requestedQuantity) {
	            product.setQuantity(product.getQuantity() - requestedQuantity);
	            productrepo.save(product);
	            return "Order placed successfully";
	        } else {
	            return "Insufficient quantity";
	        }
	    } else {
	        return "Product not found";
	    }
	}
	public String restoreQuantity(Long productId, int quantityToAdd) {
	    Optional<Product> productOpt = productrepo.findById(productId);

	    if (productOpt.isPresent()) {
	        Product product = productOpt.get();
	        product.setQuantity(product.getQuantity() + quantityToAdd);
	        productrepo.save(product);
	        return "Quantity restored successfully.";
	    } else {
	        return "Product not found.";
	    }
	}
	
	
}
