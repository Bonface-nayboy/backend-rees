package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final StockRepository stockRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, StockRepository stockRepository, RegisterRepository registerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.stockRepository = stockRepository;
        this.registerRepository = registerRepository;
    }

    @Transactional
    public List<Purchase> makeMultiplePurchases(String email, List<Purchase> purchases) {
        // Check if the email exists in the register repository
        boolean emailExists = registerRepository.findByEmail(email).isPresent();
        if (!emailExists) {
            throw new IllegalArgumentException("Email not found in the Register collection. Cannot proceed with purchases.");
        }

        // Process each purchase individually
        for (Purchase purchase : purchases) {
            purchase.setEmail(email); // Assign email to each purchase
            try {
                makePurchase(purchase); // Process each purchase
            } catch (Exception e) {
                System.err.println("Error processing purchase for product ID " + purchase.getProductId() + ": " + e.getMessage());
                // Log and proceed with other purchases
            }
        }
        return purchases;
    }

    public Purchase makePurchase(Purchase purchase) {
        // Verify email
        String email = purchase.getEmail();
        if (!registerRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email not found in the Register collection. Cannot make a purchase.");
        }

        // Fetch stock for product
        Stock stock = stockRepository.findByProductId(purchase.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found in stock for ID: " + purchase.getProductId()));

        // Update stock quantity (increase it)
        int newQuantity = stock.getQuantity() + purchase.getQuantity();
        stock.setQuantity(newQuantity);
        stockRepository.save(stock); // Save updated stock

        // Log the new stock quantity for verification
        System.out.println("Updated stock for product ID " + purchase.getProductId() + ": " + newQuantity);

        // Process the purchase
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setTotalPrice(purchase.getBuyPrice() * purchase.getQuantity());
        return purchaseRepository.save(purchase);
    }

    public void deletepurchase(String id, String email) {
        // Use the Purchase class instead of Purchases
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found with ID: " + id));

        // Check if the email matches the purchase's email
        if (!purchase.getEmail().equals(email)) {
            throw new IllegalArgumentException("You cannot delete a purchase that does not belong to you.");
        }

        // Delete the purchase
        purchaseRepository.delete(purchase);
    }


}