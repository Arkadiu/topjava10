package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.LocalDateTime.now;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Alexander on 12.04.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    private List<Meal> MEALS = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        MEALS = Stream.of(MEAL100002,
                MEAL100003,
                MEAL100004,
                MEAL100005,
                MEAL100006,
                MEAL100007)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Test
    public void save() throws Exception {
        Meal newMeal = new Meal(now(), "Тест-Ужин", 790);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MEALS.add(0, newMeal);
        MATCHER.assertCollectionEquals(MEALS, service.getAll(USER_ID));
    }

    @Test
    public void get() throws Exception {
        Meal meal = service.get(MEAL100007_ID, USER_ID);
        MATCHER.assertEquals(MEALS.get(0), meal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MEAL100007_ID, USER_ID);
        MEALS.remove(0);
        MATCHER.assertCollectionEquals(service.getAll(USER_ID), MEALS);
    }

    @Test
    public void getBetweenDates() throws Exception {
        LocalDate start = LocalDate.of(2015, 5, 30);
        LocalDate end = LocalDate.of(2015, 5, 30);
        Collection<Meal> filtered = MEALS.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), start, end))
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(filtered, service.getBetweenDates(start, end, USER_ID));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        LocalDateTime start = LocalDateTime.of(2015, 5, 30, 9, 0);
        LocalDateTime end = LocalDateTime.of(2015, 5, 30, 13, 0);
        Collection<Meal> filtered = MEALS.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), start, end))
                .collect(Collectors.toList());
        MATCHER.assertCollectionEquals(filtered, service.getBetweenDateTimes(start, end, USER_ID));
    }

    @Test
    public void getAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(MEALS, all);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal();
        updated.setCalories(MEALS.get(0).getCalories());
        updated.setDateTime(MEALS.get(0).getDateTime());
        updated.setDescription(MEALS.get(0).getDescription());
        updated.setId(MEALS.get(0).getId());
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEALS.get(0).getId(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteFromAnotherUser() {
        service.delete(MEAL100007_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateFromAnotherUser() {
        Meal meal = service.get(MEAL100002_ID, USER_ID);
        service.update(meal, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getFromAnotherUser() {
        service.get(MEAL100007_ID, ADMIN_ID);
    }
}