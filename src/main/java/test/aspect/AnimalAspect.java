package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.Animal;
import test.FoodBox;
import test.dto.Food;

import java.time.LocalDateTime;

@Aspect
@Component
public class AnimalAspect {
    private final FoodBox foodBox;

    @Autowired
    public AnimalAspect(FoodBox foodBox) {
        this.foodBox = foodBox;
    }

    @Pointcut("execution(* test.Animal.eat(..))")
    public void eatPoint() {
    }

    @Pointcut("within(test.Fish)")
    public void fishPoint() {
    }


    @AfterThrowing(value = "eatPoint()", throwing = "ex")
    public void eatFailed(Throwable ex) {
        System.out.println("eat failed: " + ex.getMessage());
    }


    @Around(value = "eatPoint() && args(food)")
    public Object eatAround(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        Object target = proceedingJoinPoint.getTarget();
        String targetName = target.getClass().toString();

        //is food not present
        if (food == null) {
            return false;
        }

        //validate expired food
        if (LocalDateTime.now().isAfter(food.getExpirationDate())) {
            System.out.println("Food " + food.getType() + " is expired!");
            //remove expired food
            foodBox.poll();
            return false;
        }

        try {
            Animal animal = (Animal) target;
            //if animal supports this kind of food
            if (animal.allowedFoodTypes().contains(food.getType())) {
                Object result = proceedingJoinPoint.proceed();
                System.out.println(targetName + " eat complete");
                foodBox.poll();
                return result;
            } else {
                System.out.println(targetName + " can't eat " + food);
                //poll from queue start
                Food notAcceptedFood = foodBox.poll();
                //put to the queue end
                foodBox.add(notAcceptedFood);
            }

            return false;
        } catch (Throwable e) {
            System.out.println(targetName + " eat failed: " + e.getMessage());
            throw e;
        }
    }
}
