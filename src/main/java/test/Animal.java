package test;

import test.dto.Food;

import java.util.List;

public interface Animal {
    List<Food.Type> allowedFoodTypes();

    void voice();

    boolean eat(Food food);

    boolean isAngry();
}
