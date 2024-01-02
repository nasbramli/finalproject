package com.sctp.jpa.service;

import com.sctp.jpa.model.Product;
import com.sctp.jpa.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    public ProductServiceImpl(@Autowired ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Optional<Product> updateProduct(String id, Product product){
        Optional<Product> result = productRepo.findById(id);

        try{
            Product temp = result.get();
            temp.setName(product.getName());
            return Optional.of(productRepo.save(temp));
        }catch(Exception e){
            return result;
        }
    }

    @Override
    public Optional<Product> deleteProduct(String id){
        Optional<Product> result = productRepo.findById(id);
        if(result.isPresent()){
            productRepo.deleteById(id);
        }
        return result;
    }

    @Override
    public Collection<Product> getProducts(){
        return productRepo.findAll();
    }

    public Collection<Product> getProductsByName(String name){
        return productRepo.findByNameContaining(name);
    }

    @Override
    public Optional<Product> getProduct(String id){
        return productRepo.findById(id);
    }

}
