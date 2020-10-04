package test;

import test.dto.Food;

import java.time.LocalDateTime;
import java.util.List;

public interface Animal {
    List<Food.Type> allowedFoodTypes();

    void voice();

    boolean eat(Food food);

    boolean isHungry();

    LocalDateTime getNextHungry();
}
