package at.refugeesCode.kitchenchefbackend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Meal {

    @Id
    private String id;
    private String cookName;
    private String mealName;
    private String mealDescription;
    private List<String> ingredients = new ArrayList<>();

    private int year;
    private int month;
    private int day;

    private int numberOfPeople;
    private LocalTime startCookingTime;
    private LocalTime startEatingTime;
    private Long preparationTime;
    private String dateTime;
    private String foodImage;


    public Meal() {
    }

    public Meal(String cookName, String mealName, String mealDescription, List<String> ingredients, int year, int month, int day, int numberOfPeople, LocalTime startCookingTime, LocalTime startEatingTime, Long preparationTime, String dateTime, String foodImage) {
        this.cookName = cookName;
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.ingredients = ingredients;
        this.year = year;
        this.month = month;
        this.day = day;
        this.numberOfPeople = numberOfPeople;
        this.startCookingTime = startCookingTime;
        this.startEatingTime = startEatingTime;
        this.preparationTime = preparationTime;
        this.dateTime = dateTime;
        this.foodImage = foodImage;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id='" + id + '\'' +
                ", cookName='" + cookName + '\'' +
                ", mealName='" + mealName + '\'' +
                ", mealDescription='" + mealDescription + '\'' +
                ", ingredients=" + ingredients +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", numberOfPeople=" + numberOfPeople +
                ", startCookingTime=" + startCookingTime +
                ", startEatingTime=" + startEatingTime +
                ", preparationTime=" + preparationTime +
                ", dateTime='" + dateTime + '\'' +
                ", foodImage='" + foodImage + '\'' +
                '}';
    }
}