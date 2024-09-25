package fruityvice;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FruitService {
    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    public Nutritions getNutritionsByFruitName(String name) throws IOException {
        return fruitRepository.getFruitByName(name).getNutritions();
    }

    public Map<String,Nutritions> getNutritionsForAllFruits() throws IOException {
        List<Fruit> fruits = fruitRepository.getAllFruits();
        return fruits.stream().collect(Collectors.toMap(Fruit::getName, Fruit::getNutritions));
    }
}
