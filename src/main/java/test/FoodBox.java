package test;

import org.springframework.stereotype.Component;
import test.dto.Food;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class FoodBox {
    private Queue<Food> foodBox = new LinkedList<>();

    public void add(Food food) {
        foodBox.add(food);
    }

    public Food peek() {
        return foodBox.peek();
    }

    public Food poll() {
        return foodBox.poll();
    }

    public int size() {
        return foodBox.size();
    }

}
