package com.springboot.springbootproject.miniApp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Model {
    @Id
    private String id;
    private String productId;
    private String product_name;
    private String product_price;
    private String product_image;
    private double buyPrice;
    private String category;
    private int stock;
    private String email;

    public Model() {}

    public Model(String productId, String product_name, String product_price, String product_image, double buyPrice, String category, String email) {
        this.productId = productId;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
        this.buyPrice = buyPrice;
        this.category = category;
        this.email = email;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_image='" + product_image + '\'' +
                ", buyPrice=" + buyPrice +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", email='" + email + '\'' +
                '}';
    }
}
