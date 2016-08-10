package litvin.controllers.users;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.constants.Notifications;
import litvin.controllers.AbstractServlet;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.user.User;
import litvin.service.MD5Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditMyPasswordServlet", urlPatterns = "/operations/editMyPassword")
public class EditMyPasswordServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter(Constants.PASSWORD);
        String passwordCheck = req.getParameter(Constants.PASSWORD_CHECK);

        if(isEmptyOrNull(newPassword, newPassword)){
            jumpError(ConstError.EMPTY_LINE, ConstAddress.EDIT_MY_PASSWORD_PAGE, req, resp);
            return;
        }

        if (!newPassword.equals(passwordCheck)){
            jumpError(ConstError.PASSWORDS_ARE_NOT_EQUALS, ConstAddress.EDIT_MY_PASSWORD_PAGE, req, resp);
            return;
        }

        UserDao userDao = new UserDaoHibernate();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.USER);

        newPassword = MD5Encoder.getEncodedString(newPassword);
        userDao.updatePassword(user, newPassword);

        session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.PASSWORD_UPDATED);
        resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
    }
}
