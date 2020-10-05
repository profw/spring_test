package test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.Arrays;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(value = SCOPE_PROTOTYPE)
public class Dog extends AbstractAnimal {
    public Dog(){
        sound = "Woof";
    }

    @Override
    public List<Food.Type> allowedFoodTypes() {
        return Arrays.asList(Food.Type.MEAT, Food.Type.VEGETARIAN, Food.Type.CHICKEN);
    }
}
