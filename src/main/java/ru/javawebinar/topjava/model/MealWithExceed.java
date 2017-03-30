package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {

    private int id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id = MealsUtil.id++;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDateString(){
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
