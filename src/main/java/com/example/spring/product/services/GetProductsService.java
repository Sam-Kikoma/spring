package com.example.spring.product.services;

import com.example.spring.Query;
import com.example.spring.product.model.Product;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<Void,List<ProductDTO>> {

    private final ProductRepository productRepository;
    public GetProductsService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream().map(ProductDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }
}
