package dev.andrewjfei.testcontainersdemo.repository;

import dev.andrewjfei.testcontainersdemo.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
