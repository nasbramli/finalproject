package com.sctp.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="product")
public class Product {

    @Id
    @Column(name="id")
    @NotBlank(message = "Product id cannot be blank.")
    private String id;
    @Column(name="name")
    @NotBlank(message = "Product name cannot be blank.")
    private String name;

    @Column(name="content")
    @NotBlank(message = "Product content cannot be blank.")
    private String content;

    @Column(name="image")
    @NotBlank(message = "Product url cannot be blank.")
    private String image;

    public Product(){

    }

    public Product(String id, String name, String content, String image){
        this.id = id;
        this.name = name;
        this.content = content;
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
