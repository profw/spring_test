package test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import test.Animal;
import test.FoodBox;
import test.dto.Food;
import test.event.HungryEvent;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodBox foodBox;
    private final int refillAmount;

    @Autowired
    public FoodServiceImpl(@Qualifier("foodBox") FoodBox foodBox,
                           @Value("${foodService.refillAmount}") int refillAmount) {
        this.foodBox = foodBox;
        this.refillAmount = refillAmount;
        System.out.println("refillAmount: " + refillAmount);
    }

    private static final List<Food.Type> VALUES =
            Collections.unmodifiableList(Arrays.asList(Food.Type.values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Food.Type randomFoodType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @Override
    public void add(List<Food> foods) {
        if (foods != null) {
            for (Food food : foods) {
                foodBox.add(food);
            }
        }
    }


    @Scheduled(fixedDelay = 20000)
    public void refill() {
        for (int i = 0; i < refillAmount; i++) {
            Food.Type type = randomFoodType();
            Food newFood = new Food();
            newFood.setType(type);
            newFood.setExpirationDate(LocalDateTime.now().plus(2, ChronoUnit.MINUTES));
            newFood.setFoodName(LocalDateTime.now().toString());
            foodBox.add(newFood);
            System.out.println("New food added to box: " + newFood + " TOTAL: " + foodBox.size());

        }
    }


    @EventListener(HungryEvent.class)
    public void onHungryEvent(HungryEvent event) {
        Object source = event.getSource();
        if (source instanceof Animal) {
            Animal animal = (Animal)source;
            animal.eat(foodBox.peek());
            System.out.println("Box size: " + foodBox.size());
        }
    }
}
