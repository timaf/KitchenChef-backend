package at.refugeesCode.kitchenchefbackend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;

@Document
public class Food {

    @Id
    private String id;
    private String username;
    private String foodName;
    private String foodDescription;
    private String ingredients;
    private Duration preparationTime;
    private LocalDateTime dateTime;

    public Food() {
    }

    public Food(String username, String foodName, String foodDescription, String ingredients, Duration preparationTime, LocalDateTime dateTime) {
        this.username = username;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Duration getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Duration preparationTime) {
        this.preparationTime = preparationTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodDescription='" + foodDescription + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", preparationTime=" + preparationTime +
                ", dateTime=" + dateTime +
                '}';
    }
}