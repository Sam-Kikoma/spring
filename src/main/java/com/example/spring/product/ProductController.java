package com.example.spring.product;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.model.ErrorResponse;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.model.UpdateProductCommand;
import com.example.spring.product.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final UpdateProductService updateProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductsService getProductsService;
    private final SearchProductService searchProductService;

    public ProductController(CreateProductService createProductService, GetProductService getProductService, UpdateProductService updateProductService, DeleteProductService deleteProductService, GetProductsService getProductsService, SearchProductService searchProductService) {
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.updateProductService = updateProductService;
        this.deleteProductService = deleteProductService;
        this.getProductsService = getProductsService;
        this.searchProductService = searchProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id,@RequestBody Product product){
        return updateProductService.execute(new UpdateProductCommand(id,product));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getProductsService.execute(null);
    }

//   Get by ID
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id){
        return getProductService.execute(id);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        return deleteProductService.execute(id);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name){
        return searchProductService.execute(name);
    }
}
