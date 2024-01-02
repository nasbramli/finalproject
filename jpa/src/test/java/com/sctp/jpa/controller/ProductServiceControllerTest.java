package com.sctp.jpa.controller;

import com.sctp.jpa.model.Product;
import com.sctp.jpa.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest //place the @AutoConfigureMockMvc annotation on this class under test.
class ProductServiceControllerTest {

    @Autowired //
    MockMvc mockMvc;

    @MockBean //annotates that ProductService is what we want to set up as a mock service.
    private ProductService productService;

    private Product product1;
    private  Product product2;
    private Product updateProduct;

    @Autowired
    private ObjectMapper objectMapper; // used for sending data in string format when we run our tests.

    @BeforeEach //set up some initialisations before we use run the test.
    void init(){
        product1 = new Product("1", "Salmon", "salmon swimming", "data:image/jpeg;base64,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");
        product2 = new Product("2", "Trout", "trout sashimi", "https://fishme.com.au/cdn/shop/products/Tasmanian-Ocean-Trout-Sashimi_1_1024x1024.jpg?v=1648712618");
        updateProduct = new Product("1", "Norwegian Salmon", "nowegian salmon swimming", "data:image/jpeg;base64,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");
    }


    @Test //testing all products to be returned.
    void getProducts() throws Exception{
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        when(productService.getProducts()).thenReturn(list);

        this.mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(list.size())));

    }

    @Test
    void getSingleProduct() {
        //Challenge to write single product
    }

    @Test
    void updateProduct() throws Exception{
        //1. this is unit test, therefore the expected output stated here
        when(productService.updateProduct(any(String.class), any(Product.class))).thenReturn(Optional.ofNullable(product1));

        //2. the is what the unit test will perform and the expected output should
        // be the sameas the above unit test case
        this.mockMvc.perform(put("/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProduct)))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(product1.getId())))
                .andExpect(jsonPath("$.name", is(product1.getName())));
    }

    @Test
    void deleteProduct() throws Exception{
        //doNothing().when(productService).deleteProduct(anyString());

        //this.mockMvc.perform(delete("/products/{id}", "1"))
        //                .andExpect(status().isOk());

        when(productService.deleteProduct(any(String.class)))
                .thenReturn(Optional.ofNullable(product1));

        this.mockMvc.perform(delete("/products/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(product1.getId())))
                .andExpect(jsonPath("$.name", is(product1.getName())));
    }

    @Test
    void createProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product1);

        this.mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(product1.getId())))
                .andExpect(jsonPath("$.name", is(product1.getName())));
    }
}