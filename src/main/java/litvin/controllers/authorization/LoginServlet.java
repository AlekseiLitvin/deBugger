package litvin.controllers.authorization;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.controllers.AbstractServlet;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.exceptions.DaoException;
import litvin.model.user.User;
import litvin.service.MD5Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(Constants.EMAIL);
        String password = req.getParameter(Constants.PASSWORD);

        UserDao userDao = new UserDaoHibernate();
        try {
            if(isEmptyOrNull(email, password)){
                jumpError(ConstError.EMPTY_EMAIL_OR_PASSWORD, ConstAddress.LOGIN_PAGE, req, resp);
                return;
            }

            User user = userDao.getUser(email);
            password = MD5Encoder.getEncodedString(password);
            if(user == null || !userDao.checkPassword(email, password)){
                jumpError(ConstError.WRONG_EMAIL_OR_PASSWORD, ConstAddress.LOGIN_PAGE, req, resp);
                return;
            }
            if (LoggedUsers.usersSet.contains(user)){
                jumpError(ConstError.USER_ALREADY_LOGGED, ConstAddress.LOGIN_PAGE, req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute(Constants.USER, user);

            resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
        }catch (DaoException e){
            jumpError(ConstError.DAO_ERROR, ConstAddress.LOGIN_PAGE, req, resp);
        }
    }
}
