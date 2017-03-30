package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Alexander on 24.03.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private MealDao dao = new MealDao();

    private List<MealWithExceed> convertListMeal(List<Meal> list) {
        return MealsUtil
                .getFilteredWithExceeded(list, LocalTime.MIN, LocalTime.MAX, 2000);
    }

/*    public MealServlet() {
        super();
        dao = new MealDao();
    }

    private List<MealWithExceed> listFromDb = convertListMeal(dao.getAllMeals());
*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("redirect to Meals");
        request.setAttribute("meals", convertListMeal(MealsUtil.getMealIml()));
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, resp);

/*        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.deleteMeal(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", listFromDb);
        } else if (action.equalsIgnoreCase("edit")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            forward = INSERT_OR_EDIT;
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")) {
            forward = LIST_MEAL;
            request.setAttribute("meals", listFromDb);
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, resp);*/
    }

/*    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime ldt = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(ldt, description, calories);
        String mealId = req.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            dao.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            dao.updateMeal(meal);
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_MEAL);
        req.setAttribute("meals", listFromDb);
        view.forward(req, resp);
    }*/
}
