package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final StockRepository stockRepository;
    private final RegisterRepository registerRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository, StockRepository stockRepository, RegisterRepository registerRepository) {
        this.modelRepository = modelRepository;
        this.stockRepository = stockRepository;
        this.registerRepository = registerRepository;
    }

    public List<Model> getModels() {
        return modelRepository.findAll(); // Fetch all models
    }

    public List<Model> saveAll(List<Model> models) {
        // Check if email exists in Register collection
        String email = models.get(0).getEmail(); // Assume all models have the same email
        boolean emailExists = registerRepository.findByEmail(email).isPresent();

        if (!emailExists) {
            throw new IllegalArgumentException("Email not found in the Register collection. Cannot post products.");
        }

        // Loop through each model to save
        for (Model model : models) {
            stockRepository.findByProductId(model.getProductId()).ifPresentOrElse(
                    existingStock -> {
                        // Update existing stock
                        existingStock.setQuantity(existingStock.getQuantity() + model.getStock());
                        stockRepository.save(existingStock); // Save updated stock

                        // Update the model's stock to match the updated stock quantity
                        model.setStock(existingStock.getQuantity());
                        modelRepository.save(model);
                        System.out.println("Updated stock for productId: " + model.getProductId() + " to " + existingStock.getQuantity());
                    },
                    () -> {
                        // Create new stock entry if it does not exist
                        Stock newStock = new Stock(model.getProductId(), model.getProduct_name(), model.getStock(), email);
                        Stock savedStock = stockRepository.save(newStock); // Save new stock entry

                        // Set the model's stock to the new stock quantity
                        model.setStock(savedStock.getQuantity());
                        modelRepository.save(model); // Save model
                        System.out.println("Created new stock entry for productId: " + model.getProductId() + " with quantity: " + savedStock.getQuantity());
                    }
            );
        }

        return models; // Return the list of saved models
    }

    public List<Model> getModelsByEmail(String email) {
        // Fetch models by the user's email and synchronize stock
        List<Model> models = modelRepository.findByEmail(email);
        for (Model model : models) {
            // Synchronize stock with the stock collection
            stockRepository.findByProductId(model.getProductId()).ifPresent(stock -> {
                model.setStock(stock.getQuantity()); // Update model stock from stock collection
                modelRepository.save(model); // Save updated model
            });
        }
        return models; // Return updated models
    }

    public void deletemodel(String id, String email) {
        // Find the model by ID
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        // Check if the email matches
        if (!model.getEmail().equals(email)) {
            throw new IllegalArgumentException("You cannot delete a product that does not belong to you.");
        }

        // Delete the model
        modelRepository.delete(model);

        // Optionally, delete the stock associated with the model
        stockRepository.findByProductId(model.getProductId()).ifPresent(stock -> {
            stockRepository.delete(stock);
            System.out.println("Deleted stock for productId: " + model.getProductId());
        });
    }

}
