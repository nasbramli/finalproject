package com.sctp.jpa.repository;

import com.sctp.jpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    // You can even implement new method signatures to this interface
    // on top of existing ones provided by JpaRepository

    List<Product> findByNameContaining(String name);
}
