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

    /*
    @Before(value = "eatPoint()")
    public void beforeEat() {
        System.out.println("start eat");
    }

    @After(value = "eatPoint()")
    public void afterEat() {
        System.out.println("end eat");
    }
     */

    @AfterThrowing(value = "eatPoint()", throwing = "ex")
    public void eatFailed(Throwable ex) {
        System.out.println("eat failed: " + ex.getMessage());
    }

    /*
    @AfterReturning(value = "eatPoint()")
    public void eatSuccess(JoinPoint joinPoint) {
        System.out.println("eat success");
    }
     */

    @Around(value = "eatPoint() && args(food)")
    public Object eatAround(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        Object target = proceedingJoinPoint.getTarget();
        String targetName = target.getClass().toString();

        //is food expired
        if (food == null) {
            return false;
        }
        if (LocalDateTime.now().isAfter(food.getExpirationDate())){
            System.out.println("Food " + food.getFoodName() + " is expired!");
            foodBox.poll();
            return false;
        }
        //System.out.println(targetName + " start eat");
        try {
            Animal animal = (Animal)target;
            //try to
            if (animal.allowedFoodTypes().contains(food.getType())) {
                Object result = proceedingJoinPoint.proceed();
                System.out.println(targetName + " eat complete");
                foodBox.poll();
                return result;
            } else {
                System.out.println(targetName + " can't eat " + food);
                Food notAcceptedFood = foodBox.poll();
                foodBox.add(notAcceptedFood);
            }

            return false;
        } catch (Throwable e) {
            System.out.println(targetName + " eat failed: " + e.getMessage());
            throw e;
        }
    }

    /*
    @Around(value = "eatPoint() && args(food) && fishPoint()")
    public Object validateEatForFish(ProceedingJoinPoint proceedingJoinPoint, Food food) throws Throwable {
        if (Objects.equals(food.getFoodName(), "fish")) {
            return false;
        } else {
            return eatAround(proceedingJoinPoint, food);
        }
    }

     */
}
