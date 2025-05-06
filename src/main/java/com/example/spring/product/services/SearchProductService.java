package com.example.spring.product.services;

import com.example.spring.Query;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(name).stream().map(ProductDTO::new).toList());
    }
}
