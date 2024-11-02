package com.springboot.springbootproject.miniApp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Purchases")
public class Purchase {

    @Id
    private String id;
    private String productId;
    private String productName;
    private int quantity;
    private Double buyPrice;
    private Double totalPrice;
    private String paymentMethod;
    private String mobileNumber;
    private String email; // Added email field
    private LocalDateTime purchaseDate;

    // Default constructor
    public Purchase() {}

    // Parameterized constructor
    public Purchase(String productId, String productName, int quantity, Double buyPrice, Double totalPrice,
                    String paymentMethod, String mobileNumber, String email, LocalDateTime purchaseDate) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Double getBuyPrice() { return buyPrice; }
    public void setBuyPrice(Double buyPrice) { this.buyPrice = buyPrice; }
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDateTime purchaseDate) { this.purchaseDate = purchaseDate; }
}
