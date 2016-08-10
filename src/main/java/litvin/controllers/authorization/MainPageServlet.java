package litvin.controllers.authorization;


import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.controllers.ServletUtil;
import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.project.Issue;
import litvin.model.user.Role;
import litvin.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@WebServlet(urlPatterns = "/main", name = "main")
public class MainPageServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IssueDao issueDao = new IssueDaoHibernate();

        List<Issue> issuesList = new ArrayList<>();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        if (user == null){
            user = new User();
            user.setRole(Role.GUEST.name());
            session.setAttribute(Constants.USER, user);
        }

        String userRole = user.getRole();
        if (userRole.equals(Role.GUEST.name())){
            issuesList = issueDao.getAllIssues();
        }else if (userRole.equals(Role.USER.name()) || userRole.equals(Role.ADMIN.name())){
            issuesList = issueDao.getIssuesBuUser(user);
        }
        Collections.reverse(issuesList);

        req.setAttribute(Constants.ISSUES_LIST, issuesList);
        RequestDispatcher rd = req.getRequestDispatcher(ConstAddress.MAIN_PAGE);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}

