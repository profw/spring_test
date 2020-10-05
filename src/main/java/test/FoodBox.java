package test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import test.dto.Food;

import java.time.LocalDateTime;
import java.util.*;

@Component
@Scope(value = "singleton")
public class FoodBox {
    private Queue<Food> foodBox = new LinkedList<>();
    private final int maxSize;

    public FoodBox(@Value("${foodBox.maxSize}") int maxSize){
        this.maxSize = maxSize;
    }

    public void add(Food food) {
        if (foodBox.size() < maxSize) {
            foodBox.add(food);
        }
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

    public  List<Food> cleanExpired() {
        List<Food> expired = new ArrayList<>(foodBox.size());
        Iterator<Food> iterator = foodBox.iterator();
        while (iterator.hasNext()) {
            Food next = iterator.next();
            if (LocalDateTime.now().isAfter(next.getExpirationDate())) {
                iterator.remove();
                expired.add(next);
            }
        }
        return expired;
    }
}
