package litvin.controllers.authorization;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.constants.Notifications;
import litvin.controllers.AbstractServlet;
import litvin.controllers.authorization.validators.RegexValidation;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.exceptions.DaoException;
import litvin.model.user.Role;
import litvin.service.MD5Encoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegServlet", urlPatterns = "/admin/registration")
public class RegServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(Constants.FIRST_NAME);
        String lastName = req.getParameter(Constants.LAST_NAME);
        String email = req.getParameter(Constants.EMAIL);
        String role = req.getParameter(Constants.ROLE);
        String password = req.getParameter(Constants.PASSWORD);
        String passwordCheck = req.getParameter(Constants.PASSWORD_CHECK);

        String validationResult = validateData(firstName, lastName, email, role, password, passwordCheck);
        if (!validationResult.equals(ConstError.OK)) {
            jumpError(validationResult, ConstAddress.REG_PAGE, req, resp);
            return;
        }

        password = MD5Encoder.getEncodedString(password);
        UserDao userDao = new UserDaoHibernate();
        try {
            if (userDao.saveUser(firstName, lastName, email, role, password)) {
                HttpSession session = req.getSession();
                session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.USER_REGISTERED);
                resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
            } else {
                jumpError(ConstError.EMAIL_ALREADY_TAKEN, ConstAddress.REG_PAGE, req, resp);
            }
        } catch (DaoException e) {
            System.err.println(e.getMessage());
            jumpError(ConstError.DAO_ERROR, ConstAddress.REG_PAGE, req, resp);
        }
    }

    private String validateData(String firstName, String lastName, String email, String role,
                                String password, String passwordCheck) {
        if (isEmptyOrNull(firstName, lastName, email, role, password, passwordCheck)) {
            return ConstError.EMPTY_LINE;
        }
        if (!password.equals(passwordCheck)) {
            return ConstError.PASSWORDS_ARE_NOT_EQUALS;
        }
        if (!Role.USER.name().equals(role) && !Role.ADMIN.name().equals(role)) {
            return ConstError.ROLE_ERROR;
        }
        if (!RegexValidation.validateEmail(email) || !RegexValidation.validatePassword(password)) {
            return ConstError.PATTERN_ERROR;
        }
        return ConstError.OK;
    }
}
