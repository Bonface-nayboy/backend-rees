package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProductStock(String id, int quantity, String action) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if ("sale".equalsIgnoreCase(action)) {
                product.setStock(product.getStock() - quantity);
            } else if ("purchase".equalsIgnoreCase(action)) {
                product.setStock(product.getStock() + quantity);
            }
            productRepository.save(product);
            return Optional.of(product);
        }

        return Optional.empty();
    }
}
