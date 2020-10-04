package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.Arrays;
import java.util.List;

@Component
public class Tiger extends AbstractAnimal{
    public Tiger() {
        sound = "RrRRr!";
    }

    @Override
    public List<Food.Type> allowedFoodTypes() {
        return Arrays.asList(Food.Type.MEAT);
    }
}
