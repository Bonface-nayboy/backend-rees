package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestBody Sales sale, @RequestParam String email) {
        System.out.println("Received Sale Data: " + sale);
        Sales savedSale = salesService.createSale(sale, email);
        return ResponseEntity.ok(savedSale);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createMultipleSales(@RequestBody List<Sales> salesList, @RequestParam String email) {
        try {
            List<Sales> savedSales = salesService.createMultipleSales(salesList, email);
            return ResponseEntity.ok(savedSales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.getAllSales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable String id) {
        Sales sale = salesService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable String id, @RequestParam String email) {
        try {
            salesService.deleteSale(id, email);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
