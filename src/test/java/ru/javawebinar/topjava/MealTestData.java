package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID = START_SEQ ;
    public static int count = 2;

    public static final int MEAL100002_ID = MEAL_ID + count++;
    public static final int MEAL100003_ID = MEAL_ID + count++;
    public static final int MEAL100004_ID = MEAL_ID + count++;
    public static final int MEAL100005_ID = MEAL_ID + count++;
    public static final int MEAL100006_ID = MEAL_ID + count++;
    public static final int MEAL100007_ID = MEAL_ID + count++;

    public static final Meal MEAL100002 =
            new Meal(MEAL100002_ID, LocalDateTime.of(2015, 5, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL100003 =
            new Meal(MEAL100003_ID, LocalDateTime.of(2015, 5, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL100004 =
            new Meal(MEAL100004_ID, LocalDateTime.of(2015, 5, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL100005 =
            new Meal(MEAL100005_ID, LocalDateTime.of(2015, 5, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL100006 =
            new Meal(MEAL100006_ID, LocalDateTime.of(2015, 5, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL100007 =
            new Meal(MEAL100007_ID, LocalDateTime.of(2015, 5, 31, 20, 0), "Ужин", 510);

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            ((expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                            && Objects.equals(expected.getCalories(), actual.getCalories())
            )
    );

}
