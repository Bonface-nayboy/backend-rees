//package com.springboot.springbootproject.miniApp;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path = "api/v1/model")
//public class ModelController {
//
//    private final ModelService modelService;
//
//    @Autowired
//    public ModelController(ModelService modelService) {
//        this.modelService = modelService;
//    }
//
//    @GetMapping
//    public List<Model> getModels(@RequestParam String email) {
//        return modelService.getModelsByEmail(email);  // Fetch models by email
//    }
//
//    @PostMapping
//    public List<Model> createModel(@RequestBody List<Model> models, @RequestParam String email) {
//        models.forEach(model -> model.setEmail(email)); // Associate email with each model
//        try {
//            return modelService.saveAll(models); // Save models
//        } catch (IllegalArgumentException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//}










package com.springboot.springbootproject.miniApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/model")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public List<Model> getModels(@RequestParam String email) {
        return modelService.getModelsByEmail(email);
    }

    @PostMapping
    public List<Model> createModel(@RequestBody List<Model> models, @RequestParam String email) {
        models.forEach(model -> model.setEmail(email));
        try {
            return modelService.saveAll(models);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModel(@PathVariable String id, @RequestParam String email) {
        try {
            modelService.deletemodel(id, email);
            return ResponseEntity.noContent().build(); // Respond with 204 No Content on success
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage()); // Respond with 403 Forbidden
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage()); // Respond with 404 Not Found
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }

}

