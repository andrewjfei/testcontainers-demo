package dev.andrewjfei.testcontainersdemo.controller;

import dev.andrewjfei.testcontainersdemo.BaseIT;
import dev.andrewjfei.testcontainersdemo.entity.Ingredient;
import dev.andrewjfei.testcontainersdemo.repository.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientControllerIT extends BaseIT {

    private final String INGREDIENT_URI = "/api/ingredient";

    private final int INGREDIENT_LIST_SIZE = 4;

    private final Long INGREDIENT_1_ID = Long.valueOf(1);
    private final Long INGREDIENT_2_ID = Long.valueOf(2);
    private final Long INGREDIENT_3_ID = Long.valueOf(3);
    private final Long INGREDIENT_4_ID = Long.valueOf(4);

    private final String INGREDIENT_1_NAME = "Paprika";
    private final String INGREDIENT_2_NAME = "Saffron";
    private final String INGREDIENT_3_NAME = "Cumin";
    private final String INGREDIENT_4_NAME = "Pepper";

    @Autowired
    private IngredientController ingredientController;

    @Autowired
    private IngredientRepository ingredientRepository;

    @BeforeEach
    public void setUp() {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_1_ID);
        ingredient1.setName(INGREDIENT_1_NAME);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_2_ID);
        ingredient2.setName(INGREDIENT_2_NAME);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(INGREDIENT_3_ID);
        ingredient3.setName(INGREDIENT_3_NAME);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setId(INGREDIENT_4_ID);
        ingredient4.setName(INGREDIENT_4_NAME);

        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);
        ingredientRepository.save(ingredient4);
    }

    @Test
    public void addNewIngredient_success_returnsIngredient() {
        // Given
        String ingredientName = "Salt";

        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredientName);

        HttpEntity<Ingredient> request = new HttpEntity<>(newIngredient);

        // When
        ResponseEntity<Ingredient> response = testRestTemplate.exchange(
                INGREDIENT_URI,
                HttpMethod.POST,
                request,
                Ingredient.class
        );

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ingredientName, response.getBody().getName());
    }

    @Test
    public void fetchAllIngredients_success_returnsIngredientList() {
        // Given
        HttpEntity<Void> request = new HttpEntity<>(null);

        // When
        ResponseEntity<List<Ingredient>> response = testRestTemplate.exchange(
                INGREDIENT_URI,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<>() {}
        );

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(INGREDIENT_LIST_SIZE, response.getBody().size());
    }

    @Test
    public void fetchIngredientById_success_returnsIngredient() {
        // Given
        HttpEntity<Void> request = new HttpEntity<>(null);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", INGREDIENT_1_ID.toString());

        // When
        ResponseEntity<Ingredient> response = testRestTemplate.exchange(
                INGREDIENT_URI + "/{id}",
                HttpMethod.GET,
                request,
                Ingredient.class,
                uriVariables
        );

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(INGREDIENT_1_ID, response.getBody().getId());
        Assertions.assertEquals(INGREDIENT_1_NAME, response.getBody().getName());
    }


}
