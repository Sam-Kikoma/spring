package com.example.spring;

import com.example.spring.product.ProductRepository;
import com.example.spring.product.model.Product;
import com.example.spring.product.model.ProductDTO;
import com.example.spring.product.model.UpdateProductCommand;
import com.example.spring.product.services.UpdateProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UpdateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private UpdateProductService updateProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_update_product_should_update_saved_product_and_return_product_dto(){
        //Given
        Product product = new Product();
        product.setName("Yet another test product");
        product.setDescription("This is my super duper product desc that's updates");
        product.setPrice(420.69);
        product.setId(2);
        UpdateProductCommand command = new UpdateProductCommand(product.getId(), product);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        //When
        ResponseEntity<ProductDTO> response = updateProductService.execute(command);
        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ProductDTO(product), response.getBody());
    }


}
