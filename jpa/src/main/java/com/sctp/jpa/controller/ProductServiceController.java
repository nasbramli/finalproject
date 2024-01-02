package com.sctp.jpa.controller;

import com.sctp.jpa.exception.ResourceNotFoundException;
import com.sctp.jpa.model.Product;
import com.sctp.jpa.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080")
public class ProductServiceController {

    ProductService productService;

    public ProductServiceController(@Autowired ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getProducts(@RequestParam(required = false) String title) {

        Collection<Product> result;

        if(title == null){
            result = productService.getProducts();
        }else
        {
            result = productService.getProductsByName(title);
        }

        if(!result.isEmpty()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No Product Available", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getSingleProduct(@PathVariable("id") String id){

        Product result = productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id ,@Valid @RequestBody Product product) {

        Product result = productService.updateProduct(id, product).orElseThrow(() -> new ResourceNotFoundException(id));

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);

    }
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);

    }
}
