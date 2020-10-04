package test;

import test.dto.Food;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class AbstractAnimal implements Animal{
    protected boolean hungry = true;
    protected String sound;
    protected int hungryMin = 1;
    private LocalDateTime nextHungry = LocalDateTime.now();


    @Override
    public boolean eat(Food food) {
        if (food == null)
            return false;
        hungry = false;
        System.out.println(this.getClass() + " EAT " + food);
        nextHungry = getNextHungry().plus(hungryMin, ChronoUnit.MINUTES);
        return isHungry();
    }

    @Override
    public boolean isHungry() {
        if (LocalDateTime.now().isAfter(nextHungry)) {
            hungry = true;
        }
        return hungry;
    }


    @Override
    public void voice() {
        System.out.println(this.getClass().toString() + ": " + sound);
    }

    public LocalDateTime getNextHungry() {
        return nextHungry;
    }
}
