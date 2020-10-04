package test;

import test.dto.Food;

public abstract class AbstractAnimal implements Animal{
    protected boolean angry = true;
    protected String sound;

    @Override
    public boolean eat(Food food) {
        angry = false;
        return isAngry();
    }

    @Override
    public boolean isAngry() {
        return angry;
    }


    @Override
    public void voice() {
        System.out.println(this.getClass().toString() + ": " + sound);
    }
}
