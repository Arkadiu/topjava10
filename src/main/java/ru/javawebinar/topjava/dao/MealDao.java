package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander on 29.03.2017.
 */
public class MealDao {

    private Connection connection;
    private static final String INSERT = "INSERT INTO meals(date, description, calories) VALUE (?, ?, ?)";
    private static final String DELETE = "DELETE FROM meals WHERE mealid=?)";
    private static final String UPDATE = "UPDATE meals SET date=?, description=?, calories=?)" + "WHERE mealid=?";
    private static final String GET_ALL = "SELECT * FROM meals";
    private static final String GET_BY_ID = "SELECT * FROM meals WHERE mealid=?";

    public MealDao() {
        connection = DbUtil.getConnection();
    }

    public void addMeal(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(INSERT);
            // Parameters start with 1
            preparedStatement.setTimestamp(1, Timestamp.valueOf(meal.getDateTime()));
            preparedStatement.setString(2, meal.getDescription());
            preparedStatement.setInt(3, meal.getCalories());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMeal(int mealId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE);
            // Parameters start with 1
            preparedStatement.setInt(1, mealId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMeal(Meal meal) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE);
            // Parameters start with 1
            preparedStatement.setTimestamp(1, Timestamp.valueOf(meal.getDateTime()));
            preparedStatement.setString(2, meal.getDescription());
            preparedStatement.setInt(3, meal.getCalories());
            preparedStatement.setInt(4, meal.getMealId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Meal> getAllMeals(){
        List<Meal> meals = new ArrayList<>();
        try{
            Statement statement =  connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL);
            while (rs.next()){
                int mealId = rs.getInt("mealid");
                LocalDateTime localDateTime = rs.getTimestamp("date").toLocalDateTime();
                String description = rs.getString("description");
                int calories = rs.getInt("calories");
                Meal meal = new Meal(localDateTime, description, calories);
                meal.setMealId(mealId);
                meals.add(meal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meals;
    }

    public Meal getMealById(int mealId){
        Meal meal = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_BY_ID);
            preparedStatement.setInt(1, mealId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                LocalDateTime localDateTime = rs.getTimestamp("date").toLocalDateTime();
                String description = rs.getString("description");
                int calories = rs.getInt("calories");
                meal = new Meal(localDateTime, description, calories);
                meal.setMealId(mealId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meal;
    }
}
