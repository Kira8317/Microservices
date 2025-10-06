package com.example.demo.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.ProductRepo.Product;
import com.example.demo.ProductRepo.ProductRepo;

/**
 * Service class for managing product operations.
 * Provides CRUD methods, quantity checking, and quantity restoration.
 */
@Service
public class ProductService {
    @Autowired
    ProductRepo productrepo;

    /**
     * Adds a new product to the database.
     *
     * @param pro the product to be added
     */
    public void addProduct(Product pro) {
        productrepo.save(pro);
    }

    /**
     * Deletes all products from the database.
     *
     * @return confirmation message after deletion
     */
    public String deleteProduct() {
        productrepo.deleteAll();
        return "Deleted all";
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID
     * @return an Optional containing the product if found, otherwise empty
     */
    public Product findById(Long id) {
        return productrepo.findById(id)	
        .orElseThrow(() -> new ResourceNotFoundException("Product with ID " + id + " not found"));

    }


    /**
     * Retrieves all products from the database.
     *
     * @return list of all products
     */
    public List<Product> getProduct() {
        return productrepo.findAll();
    }

    /**
     * Deletes a specific product by its ID.
     *
     * @param id the ID of the product to delete
     */
    public void deleteById(Long id) {
        productrepo.deleteById(id);
    }

    /**
     * Updates the price of a product by its ID.
     *
     * @param id the ID of the product to update
     * @param updatedprice the new price to set
     * @return the updated product, or null if not found
     */
    public Product updateById(Long id, int updatedprice) {
        return productrepo.findById(id)
                .map(product -> {
                    product.setPrice(updatedprice);
                    return productrepo.save(product);
                })
                .orElse(null);
    }

    /**
     * Checks if the product has enough quantity and updates it if possible.
     *
     * @param productId the ID of the product
     * @param requestedQuantity the quantity to reserve
     * @return message indicating the result (success, insufficient stock, or not found)
     */
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

    /**
     * Restores quantity to a product's stock.
     *
     * @param productId the ID of the product
     * @param quantityToAdd the quantity to restore
     * @return message indicating success or failure
     */
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
