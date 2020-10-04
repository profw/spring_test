package test.dto;

import java.time.LocalDateTime;

public class Food {
    public enum Type {
        ALL,
        VEGETARIAN,
        MEAT,
        SEAFOOD,

    }

    private Type type;
    private String foodName;
    private LocalDateTime expirationDate;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
