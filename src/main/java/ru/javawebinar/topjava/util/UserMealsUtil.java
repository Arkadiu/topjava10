package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<MealWithExceed> result = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.println(result);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<MealWithExceed> listResult = new ArrayList<>();
/*
        List<Meal> tempList = new ArrayList<>();
        int exceededCalories = 0;

        for (Meal userMeal : mealList) {
            LocalTime localTime = userMeal.getDateTime().toLocalTime();
            if (TimeUtil.isBetween(localTime, startTime, endTime)) {
                tempList.add(userMeal);
                exceededCalories += userMeal.getCalories();
            }
        }

        for (Meal user : tempList)
            listResult.add(createMealWithExceed(meal, exceedCalories > caloriesPerDay));

*/
        int exceedCalories = mealList
                .stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .mapToInt(meal -> meal.getCalories())
                .sum();

        listResult = mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal ->
                        createMealWithExceed(meal, exceedCalories > caloriesPerDay))
                .collect(Collectors.toList());
        return listResult;
    }

    public static MealWithExceed createMealWithExceed(Meal meal, Boolean exceed) {
        return new MealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }
}
