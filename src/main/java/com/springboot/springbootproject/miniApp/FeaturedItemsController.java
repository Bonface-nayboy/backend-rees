package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FeaturedItemsController {

    @Autowired
    private ModelRepository modelRepository;

    // Endpoint to fetch featured items based on the selected item category
    @GetMapping("/featured-items/{id}")
    public List<Model> getFeaturedItems(@PathVariable String id) {
        Model selectedItem = modelRepository.findById(id).orElse(null);

        if (selectedItem == null) {
            throw new RuntimeException("Item not found");
        }

        // Fetching featured items based on the category of the selected item
        return modelRepository.findByCategory(selectedItem.getCategory());
    }
}
