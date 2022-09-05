package dev.andrewjfei.testcontainersdemo.service;

import dev.andrewjfei.testcontainersdemo.entity.Ingredient;
import dev.andrewjfei.testcontainersdemo.repository.IngredientRepository;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    private Logger logger = LoggerFactory.getLogger(IngredientService.class);

    public Ingredient createNewIngredient(Ingredient ingredient) {
        logger.info("Creating new ingredient...");
        Ingredient newIngredient = new Ingredient(ingredient.getName());
        return ingredientRepository.save(newIngredient);
    }

    public List<Ingredient> retrieveAllIngredients() {
        logger.info("Retrieving all ingredients...");
        Iterable<Ingredient> ingredientIterable = ingredientRepository.findAll();

        return IterableUtils.toList(ingredientIterable);
    }

    public Ingredient retrieveIngredientById(int id) {
        logger.info("Retrieving ingredient...");
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

        try {
            return optionalIngredient.get();
        } catch (Exception e) {
            logger.info("Invalid ingredient id.");
            return null;
        }
    }

}
