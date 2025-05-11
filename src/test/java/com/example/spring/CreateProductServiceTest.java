package com.example.spring;

import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.services.CreateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void given_new_product_should_save_product_and_return_product_dto(){
//        Given
        Product product = new Product();
        product.setName("Yet another test product");
        product.setDescription("This is my super duper product desc");
        product.setPrice(420.69);
        product.setId(2);
        when(productRepository.save(product)).thenReturn(product);
//        When
        ResponseEntity<ProductDTO> response = createProductService.execute(product);

//        Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(new ProductDTO(product), response.getBody());
        verify(productRepository,times(1)).save(product);
    }
}
