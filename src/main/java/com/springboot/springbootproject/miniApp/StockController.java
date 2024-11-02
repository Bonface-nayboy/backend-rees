package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getStockByEmail(@RequestParam String email) {
        return stockService.getStockByEmail(email);  // Fetches stock from StockService
    }

    @PostMapping
    public Stock addStock(@RequestParam String productId, @RequestParam String productName,
                          @RequestParam int quantity, @RequestParam String email) {
        return stockService.addStock(productId, productName, quantity, email);
    }

    @PostMapping("/increase/{productId}/{quantity}")
    public void increaseStock(@PathVariable String productId, @PathVariable int quantity, @RequestParam String email) {
        stockService.increaseStock(productId, quantity, email);
    }

    @PostMapping("/decrease/{productId}/{quantity}")
    public void decreaseStock(@PathVariable String productId, @PathVariable int quantity, @RequestParam String email) {
        stockService.decreaseStock(productId, quantity, email);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable String id, @RequestParam String email) {
        try {
            stockService.deletestock(id, email);
            return ResponseEntity.noContent().build(); // Correctly returning ResponseEntity
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
