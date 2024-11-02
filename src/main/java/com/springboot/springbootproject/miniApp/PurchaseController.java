package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // Create a new purchase
    @PostMapping
    public Purchase makePurchase(@RequestBody Purchase purchase) {
        return purchaseService.makePurchase(purchase);
    }

    // Create multiple purchases
    @PostMapping("/multiple")
    public List<Purchase> makeMultiplePurchases(@RequestParam String email, @RequestBody List<Purchase> purchases) {
        return purchaseService.makeMultiplePurchases(email, purchases);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable String id, @RequestParam String email) {
        try {
            purchaseService.deletepurchase(id, email);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }






    // Retrieve all purchases
//    @GetMapping
//    public List<Purchase> getAllPurchases() {
//        return purchaseService.getAllPurchases();
//    }
//
//    // Retrieve a purchase by ID
//    @GetMapping("/{id}")
//    public Purchase getPurchaseById(@PathVariable String id) {
//        return purchaseService.getPurchaseById(id);
//    }
//
//    // Update an existing purchase
//    @PutMapping("/{id}")
//    public Purchase updatePurchase(@PathVariable String id, @RequestBody Purchase updatedPurchase) {
//        return purchaseService.updatePurchase(id, updatedPurchase);
//    }
//
//    // Delete a purchase
//    @DeleteMapping("/{id}")
//    public void deletePurchase(@PathVariable String id) {
//        purchaseService.deletePurchase(id);
//    }
}
