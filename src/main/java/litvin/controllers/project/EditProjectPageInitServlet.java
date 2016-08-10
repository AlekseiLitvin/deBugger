package litvin.controllers.project;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.controllers.ServletUtil;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.project.Project;
import litvin.model.user.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditProjectPageInitServlet", urlPatterns = "/admin/editProject/*")
public class EditProjectPageInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String PROJECT_ATTRIBUTE = "project";
        String projectIdSting = ServletUtil.getPathVariable(req);
        ProjectDao projectDao = new ProjectDaoHibernate();
        int projectId = Integer.parseInt(projectIdSting);
        Project project = projectDao.findProjectById(projectId);
        UserDao userDao = new UserDaoHibernate();
        List<User> usersList = userDao.getAllUsers();

        req.setAttribute(PROJECT_ATTRIBUTE, project);
        req.setAttribute(Constants.USERS_LIST, usersList);

        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.PROJECT_PAGE);
        dispatcher.forward(req, resp);
    }

}
