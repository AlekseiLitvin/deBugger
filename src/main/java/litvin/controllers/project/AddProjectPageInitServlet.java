package litvin.controllers.project;


import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "AddProjectPageInitServlet", urlPatterns = "/admin/addProjectPage")
public class AddProjectPageInitServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDaoHibernate();
        List<User> usersList = userDao.getAllUsers();
        req.setAttribute(Constants.USERS_LIST, usersList);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.PROJECT_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
