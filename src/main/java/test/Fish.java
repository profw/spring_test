package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.Arrays;
import java.util.List;

@Component
public class Fish extends AbstractAnimal{
    public Fish() {
        sound = "";
    }

    @Override
    public List<Food.Type> allowedFoodTypes() {
        return Arrays.asList(Food.Type.SEAFOOD);
    }
}
