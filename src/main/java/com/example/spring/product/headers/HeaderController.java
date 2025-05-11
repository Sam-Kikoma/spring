package com.example.spring.product.headers;

import com.example.spring.product.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region){
        if(region.equals("US")) return "Bald eagle freedom!Merrricaa";
        if(region.equals("CAN")) return "Tim Hortons fella";
        return "Country Not Supported";
    }

    @GetMapping(value = "/header/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct(){
        Product product = new Product();
        product.setName("Test prod");
        product.setId(1);
        product.setDescription("Once more, another test desc");
        product.setPrice(420.69);
        return ResponseEntity.ok(product);
    }
}
