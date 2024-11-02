//package com.springboot.springbootproject.miniApp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class StockService {
//
//    private final StockRepository stockRepository;
//    private final ModelRepository modelRepository;
//    private final RegisterService registerService;
//
//    @Autowired
//    public StockService(StockRepository stockRepository, ModelRepository modelRepository, RegisterService registerService) {
//        this.stockRepository = stockRepository;
//        this.modelRepository = modelRepository;
//        this.registerService = registerService;
//    }
//
//    public List<Stock> getStockByEmail(String email) {
//        return stockRepository.findByEmail(email);
//    }
//
//    public Stock addStock(String productId, String productName, int quantity, String email) {
//        verifyEmail(email);
//
//        Stock stock = stockRepository.findByProductIdAndEmail(productId, email)
//                .orElse(new Stock(productId, productName, 0, email)); // Initialize with 0 if no stock entry
//
//        stock.setQuantity(stock.getQuantity() + quantity); // Add to existing quantity
//        Stock savedStock = stockRepository.save(stock);
//
//        // Synchronize with Products collection
//        updateModelStock(productId, savedStock.getQuantity());
//        return savedStock;
//    }
//
//    public void increaseStock(String productId, int quantity, String email) {
//        verifyEmail(email);
//
//        Stock stock = stockRepository.findByProductIdAndEmail(productId, email)
//                .orElseGet(() -> new Stock(productId, "", 0, email)); // Create new if not exists
//
//        // Increase stock in the stock collection
//        stock.setQuantity(stock.getQuantity() + quantity);
//        stockRepository.save(stock);
//
//        // Synchronize with Products collection
//        updateModelStock(productId, stock.getQuantity());
//    }
//
//    public void decreaseStock(String productId, int quantity, String email) {
//        verifyEmail(email);
//
//        Stock stock = stockRepository.findByProductIdAndEmail(productId, email)
//                .orElseThrow(() -> new RuntimeException("Insufficient stock available for product ID: " + productId));
//
//        System.out.println("Current stock before decrease: " + stock.getQuantity());
//
//        if (stock.getQuantity() < quantity) {
//            throw new RuntimeException("Not enough stock available for product ID: " + productId);
//        }
//
//        stock.setQuantity(stock.getQuantity() - quantity);
//        stockRepository.save(stock);
//
//        System.out.println("Stock decreased by " + quantity + ". New stock: " + stock.getQuantity());
//
//        updateModelStock(productId, stock.getQuantity());
//    }
//
//
//    private void updateModelStock(String productId, int updatedQuantity) {
//        modelRepository.findByProductId(productId).ifPresent(model -> {
//            model.setStock(updatedQuantity); // Update the product's stock
//            modelRepository.save(model); // Save the updated model to the database
//            System.out.println("Updated product stock: " + model);
//        });
//    }
//
//
//
//
//    private void verifyEmail(String email) {
//        if (!registerService.getRegisters().stream().anyMatch(r -> r.getEmail().equals(email))) {
//            throw new RuntimeException("User not found with email: " + email);
//        }
//    }
//}








package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final ModelRepository modelRepository;
    private final RegisterService registerService;

    @Autowired
    public StockService(StockRepository stockRepository, ModelRepository modelRepository, RegisterService registerService) {
        this.stockRepository = stockRepository;
        this.modelRepository = modelRepository;
        this.registerService = registerService;
    }

    public List<Stock> getStockByEmail(String email) {
        return stockRepository.findByEmail(email);
    }

    public Stock addStock(String productId, String productName, int quantity, String email) {
        verifyEmail(email);

        Stock stock = stockRepository.findByProductIdAndEmail(productId, email)
                .orElse(new Stock(productId, productName, 0, email)); // Initialize with 0 if no stock entry

        stock.setQuantity(stock.getQuantity() + quantity); // Add to existing quantity
        Stock savedStock = stockRepository.save(stock);

        // Synchronize with Products collection
        updateModelStock(productId, savedStock.getQuantity());
        return savedStock;
    }

    public void increaseStock(String productId, int quantity, String email) {
        verifyEmail(email);

        Stock stock = stockRepository.findByProductIdAndEmail(productId, email)
                .orElseGet(() -> new Stock(productId, "", 0, email)); // Create new if not exists

        // Increase stock in the stock collection
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);

        // Synchronize with Products collection
        updateModelStock(productId, stock.getQuantity());
    }

    public void decreaseStock(String productId, int quantity, String email) {
        verifyEmail(email);

        // Attempt to find the existing stock for the product and user
        Stock stock = stockRepository.findByProductIdAndEmail(productId, email)
                .orElseThrow(() -> new RuntimeException("Product not found in stock for user with email: " + email));

        // Debug output for current stock quantity
        System.out.println("Current stock before decrease: " + stock.getQuantity());

        // Check if there is sufficient stock to perform the decrease
        if (stock.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock available for product ID: " + productId +
                    ". Available: " + stock.getQuantity() + ", Required: " + quantity);
        }

        // Decrease stock quantity
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepository.save(stock); // Save the updated stock to the database

        // Synchronize with Products collection (optional)
        updateModelStock(productId, stock.getQuantity());

        // Debug output for new stock quantity
        System.out.println("Stock decreased by " + quantity + ". New stock: " + stock.getQuantity());
    }


    private void updateModelStock(String productId, int updatedQuantity) {
        modelRepository.findByProductId(productId).ifPresent(model -> {
            model.setStock(updatedQuantity); // Update the product's stock
            modelRepository.save(model); // Save the updated model to the database
            System.out.println("Updated product stock: " + model);
        });
    }
    public void updateStock(Stock stock) {
        stockRepository.save(stock); // Save the updated stock quantity to the database
        System.out.println("Stock updated for product ID: " + stock.getProductId() + " to quantity: " + stock.getQuantity());
    }



    private void verifyEmail(String email) {
        if (!registerService.getRegisters().stream().anyMatch(r -> r.getEmail().equals(email))) {
            throw new RuntimeException("User not found with email: " + email);
        }
    }

    public void deletestock(String id, String email) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found for user with id: " + id));
        // Check if the email matches
        if (!stock.getEmail().equals(email)) {
            throw new IllegalArgumentException("You cannot delete a product that does not belong to you.");
        }
        stockRepository.delete(stock);
    }

}
