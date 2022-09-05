package dev.andrewjfei.testcontainersdemo.controller;

import dev.andrewjfei.testcontainersdemo.entity.Ingredient;
import dev.andrewjfei.testcontainersdemo.service.IngredientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    private Logger logger = LoggerFactory.getLogger(IngredientController.class);

    @PostMapping
    public ResponseEntity<Ingredient> addNewIngredient(@RequestBody Ingredient ingredient) {
        logger.info("Calling ingredient service to create new ingredient.");
        Ingredient newIngredient = ingredientService.createNewIngredient(ingredient);
        return new ResponseEntity<>(newIngredient, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> fetchAllIngredient() {
        logger.info("Calling ingredient service to retrieve all ingredients.");
        List<Ingredient> ingredientList = ingredientService.retrieveAllIngredients();
        return new ResponseEntity<>(ingredientList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> fetchIngredientById(@PathVariable int id) {
        logger.info("Calling ingredient service to retrieve ingredient by id.");
        Ingredient ingredient = ingredientService.retrieveIngredientById(id);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }
}
