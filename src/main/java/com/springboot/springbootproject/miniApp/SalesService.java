package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final StockService stockService;
    private final ModelService modelService;
    private final RegisterRepository registerRepository; // Inject RegisterRepository

    @Autowired
    public SalesService(SalesRepository salesRepository, StockService stockService, ModelService modelService, RegisterRepository registerRepository) {
        this.salesRepository = salesRepository;
        this.stockService = stockService;
        this.modelService = modelService;
        this.registerRepository = registerRepository;
    }

    public Sales createSale(Sales sale, String email) {
        Optional<Register> registerOptional = registerRepository.findByEmail(email);
        if (registerOptional.isEmpty() || registerOptional.get().getEmail() == null) {
            throw new IllegalArgumentException("Email not found in the Register collection or is null. Cannot proceed with sale.");
        }

        Register register = registerOptional.get();
        String registerEmail = register.getEmail();

        // Fetch product details to validate and calculate prices
        Model product = modelService.getModels().stream()
                .filter(model -> model.getProductId().equals(sale.getProductId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + sale.getProductId()));

        double unitPrice = Double.parseDouble(product.getProduct_price());
        double totalPrice = unitPrice * sale.getQuantity();

        // Check if enough stock is available
        Stock stock = stockService.getStockByEmail(registerEmail)
                .stream()
                .filter(s -> s.getProductId().equals(sale.getProductId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No stock found for product ID: " + sale.getProductId()));

        if (stock.getQuantity() < sale.getQuantity()) {
            throw new RuntimeException("Not enough stock available to make the sale. Available: " + stock.getQuantity());
        }

        // Set sale details
        sale.setPrice(totalPrice);
        sale.setSaleDate(LocalDateTime.now());
        sale.setEmail(registerEmail);

        // Log before decreasing stock
        System.out.println("Attempting to decrease stock for product ID: " + sale.getProductId() + " by " + sale.getQuantity());

        // Decrease the stock
        int newQuantity = stock.getQuantity() - sale.getQuantity();
        stock.setQuantity(newQuantity);
        stockService.updateStock(stock); // Ensure this method is implemented correctly

        // Log updated stock
        System.out.println("Stock decreased. New quantity for product ID " + sale.getProductId() + ": " + newQuantity);

        // Save sale
        System.out.println("Sale created for product ID: " + sale.getProductId() + " with quantity: " + sale.getQuantity());
        return salesRepository.save(sale);
    }

    public List<Sales> createMultipleSales(List<Sales> salesList, String email) {
        Optional<Register> registerOptional = registerRepository.findByEmail(email);
        if (registerOptional.isEmpty() || registerOptional.get().getEmail() == null) {
            throw new IllegalArgumentException("Email not found in the Register collection or is null. Cannot proceed with sales.");
        }

        Register register = registerOptional.get();
        String registerEmail = register.getEmail();

        for (Sales sale : salesList) {
            // Ensure you fetch product details before setting them
            Model product = modelService.getModels().stream()
                    .filter(model -> model.getProductId().equals(sale.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + sale.getProductId()));

            double unitPrice = Double.parseDouble(product.getProduct_price());
            double totalPrice = unitPrice * sale.getQuantity();

            // Set additional fields for the sale
            sale.setPrice(totalPrice); // Set the total price
            sale.setSaleDate(LocalDateTime.now()); // Set the current date and time
            sale.setEmail(registerEmail); // Set the email from the Register object

            // Log before decreasing stock
            Stock stock = stockService.getStockByEmail(registerEmail)
                    .stream()
                    .filter(s -> s.getProductId().equals(sale.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No stock found for product ID: " + sale.getProductId()));

            if (stock.getQuantity() < sale.getQuantity()) {
                throw new RuntimeException("Not enough stock available to make the sale. Available: " + stock.getQuantity());
            }

            // Decrease the stock
            int newQuantity = stock.getQuantity() - sale.getQuantity();
            stock.setQuantity(newQuantity);
            stockService.updateStock(stock); // Ensure this method is implemented correctly

            // Save the sale
            salesRepository.save(sale);
        }

        return salesList; // Return the list of saved sales
    }

    public List<Sales> getAllSales() {
        List<Sales> salesList = salesRepository.findAll();
        return salesList.stream()
                .map(sale -> {
                    sale.setSaleDate(convertUtcToLocal(sale.getSaleDate()));
                    return sale;
                })
                .collect(Collectors.toList());
    }

    public Sales getSaleById(String id) {
        Sales sale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));

        sale.setSaleDate(convertUtcToLocal(sale.getSaleDate()));
        return sale;
    }

    private LocalDateTime convertUtcToLocal(LocalDateTime utcDateTime) {
        return utcDateTime; // Modify if you have UTC conversion logic
    }
    public void deleteSale(String id, String email) {
        // Fetch the sale by ID
        Sales sale = salesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));

        // Verify that the email matches the sale's email
        if (!sale.getEmail().equals(email)) {
            throw new IllegalArgumentException("You cannot delete a sale that does not belong to you.");
        }

        // Proceed to delete the sale
        salesRepository.delete(sale);
    }

}
