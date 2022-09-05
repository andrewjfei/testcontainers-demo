package dev.andrewjfei.testcontainersdemo.repository;

import dev.andrewjfei.testcontainersdemo.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, int> {
}
