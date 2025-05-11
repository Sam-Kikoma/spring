package com.example.spring;

import com.example.spring.exceptions.ProductNotFoundException;
import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.GetProductService;
import com.example.spring.product.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetProductServiceTest {
    @Mock//What to mock response of -> dependency needed to run the test
    private ProductRepository productRepository;

    @InjectMocks//the thing we are testing
    private GetProductService getProductService;

    @BeforeEach//things we need before each test runs
    public void setup(){
        //init repo and service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_returns_product_dto(){
//        given
        Product product = new Product();
        product.setId(1);
        product.setName("Test Product");
        product.setDescription("This is a test description");
        product.setPrice(420.69);
        //says when but still part of given
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
//        when
        ResponseEntity<ProductDTO> response = getProductService.execute(1);
//        then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        //asserts productRepository was only called once
        verify(productRepository,times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_get_product_service_throw_product_not_found_exception(){
//        Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());
//        When&Then
        assertThrows(ProductNotFoundException.class, ()->getProductService.execute(1));
        verify(productRepository,times(1)).findById(1);
    }


}
