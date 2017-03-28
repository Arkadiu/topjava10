package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
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

    private List<MealWithExceed> list =
            MealsUtil.getFilteredWithExceeded(
                    MealsUtil.getMealIml(), LocalTime.MIN, LocalTime.MAX, 2000);

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to Meals");

        request.setAttribute("meals", list);
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, resp);

    }
}
