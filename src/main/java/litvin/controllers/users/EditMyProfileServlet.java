package litvin.controllers.users;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.constants.Notifications;
import litvin.controllers.AbstractServlet;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditMyProfileServlet", urlPatterns = "/operations/editMyProfile")
public class EditMyProfileServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter(Constants.FIRST_NAME);
        String lastName = req.getParameter(Constants.LAST_NAME);
        String newEmail = req.getParameter(Constants.EMAIL);
        String oldEmail = req.getParameter(Constants.OLD_EMAIL);

        if (isEmptyOrNull(firstName, lastName, newEmail)){
            jumpError(ConstError.EMPTY_LINE, ConstAddress.EDIT_MY_PROFILE_PAGE, req, resp);
            return;
        }

        UserDao userDao = new UserDaoHibernate();

        User user = userDao.getUser(oldEmail);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(newEmail);
        userDao.updateUser(user);

        HttpSession session = req.getSession();
        session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.MY_PAGE_EDITED);
        resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
    }

}
