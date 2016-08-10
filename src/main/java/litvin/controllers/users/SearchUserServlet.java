package litvin.controllers.users;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.controllers.AbstractServlet;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchUserServlet", urlPatterns = "/admin/searchUser")
public class SearchUserServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String SEARCH_QUERY = "searchQuery", DELIMITER = " ";
        final int FIRST_NAME_INDEX = 0, LAST_NAME_INDEX = 1;
        String searchQuery = req.getParameter(SEARCH_QUERY);

        List<User> usersList;
        try {
            String[] fullName = searchQuery.split(DELIMITER);
            String firstName = fullName[FIRST_NAME_INDEX];
            String lastName = fullName[LAST_NAME_INDEX];

            UserDao userDao = new UserDaoHibernate();
            usersList = userDao.findByName(firstName, lastName);
        }catch (RuntimeException e){
            usersList = new ArrayList<>();
        }

        req.setAttribute(Constants.USERS_LIST, usersList);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.USER_SEARCH_PAGE);
        dispatcher.forward(req, resp);
    }
}
