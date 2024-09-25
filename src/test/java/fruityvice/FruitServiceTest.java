package fruityvice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FruitServiceTest {
    private FruitRepository fruitRepository;
    private FruitService fruitService;

    @BeforeEach
    public void setup() {
        fruitRepository = Mockito.mock(FruitRepository.class);
        fruitService = new FruitService(fruitRepository);
    }

    @Test
    public void testGetNutritionsByFruitNameSuccess() throws IOException {
        Nutritions appleNutritions = new Nutritions(95, 0.3f, 19f, 25f, 0.5f);
        Fruit apple = new Fruit("Apple", 1, "Rosaceae", "Rosales", "Malus", appleNutritions);
        when(fruitRepository.getFruitByName("apple")).thenReturn(apple);
        Nutritions result = fruitService.getNutritionsByFruitName("apple");
        assertEquals(95, result.getCalories());
        assertEquals(0.3f, result.getFat());
        assertEquals(25f, result.getCarbohydrates());
    }

    @Test
    public void testGetNutritionByFruitNameNotFound() throws IOException {
        when(fruitRepository.getFruitByName("random")).thenThrow(new IOException("Fruit not found or incorrect name provided."));
        IOException ioe = assertThrows(IOException.class, () -> fruitService.getNutritionsByFruitName("random"));
        assertEquals("Fruit not found or incorrect name provided.", ioe.getMessage());
    }

    @Test
    public void testGetNutritionsForAllFruitsSucces() throws IOException {
        Nutritions appleNutritions = new Nutritions(95, 0.3f, 19f, 25f, 0.5f);
        Nutritions bananaNutritions = new Nutritions(105, 0.4f, 27f, 30f, 1.3f);
        Fruit apple = new Fruit("Apple", 1, "Rosaceae", "Rosales", "Malus", appleNutritions);
        Fruit banana = new Fruit("Banana", 2, "Musaceae", "Zingiberales", "Musa", bananaNutritions);

        when(fruitRepository.getAllFruits()).thenReturn(List.of(apple, banana));
        Map<String, Nutritions> result = fruitService.getNutritionsForAllFruits();
        assertEquals(2, result.size());
        assertEquals(appleNutritions, result.get("Apple"));
        assertEquals(bananaNutritions, result.get("Banana"));
    }

    @Test
    public void testGetNutritionsForAllFruitsEmpty() throws IOException {
        when(fruitRepository.getAllFruits()).thenReturn(Collections.emptyList());
        Map<String, Nutritions> result = fruitService.getNutritionsForAllFruits();
        assertTrue(result.isEmpty());
    }

}