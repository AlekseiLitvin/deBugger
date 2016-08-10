package litvin.controllers.users;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.controllers.ServletUtil;
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

@WebServlet(name = "EditUserProfileInitServlet", urlPatterns = "/admin/editUserProfilePage/*")
public class EditUserProfileInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = ServletUtil.getPathVariable(req);

        UserDao userDao = new UserDaoHibernate();
        User user = userDao.getUser(userEmail);

        req.setAttribute(Constants.USER, user);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.EDIT_USER_PROFILE_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
