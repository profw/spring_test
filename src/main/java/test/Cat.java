package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.Arrays;
import java.util.List;

@Component
public class Cat extends AbstractAnimal {
    public Cat() {
        sound = "mi";
    }

    @Override
    public List<Food.Type> allowedFoodTypes() {
        return Arrays.asList(Food.Type.MEAT, Food.Type.SEAFOOD, Food.Type.CHICKEN, Food.Type.FISH);
    }
}
