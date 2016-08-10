package litvin.controllers.project.issues;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;
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

@WebServlet(name = "AddIssuePageInitServlet", urlPatterns = "/operations/addIssuePage")
public class AddIssuePageInitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int NEW_STATUES_INDEX = 0, ASSIGNED_STATUS_INDEX = 1;
        AttrDao attrDao = new AttrDaoFile();
        String[] statuses = attrDao.getAllAttributes(Constants.STATUSES_FILE);
        String[] onCreateStatuses = {statuses[NEW_STATUES_INDEX], statuses[ASSIGNED_STATUS_INDEX]};
        String[] types = attrDao.getAllAttributes(Constants.TYPES_FILE);
        String[] priorities = attrDao.getAllAttributes(Constants.PRIORITIES_FILE);

        ProjectDao projectDao = new ProjectDaoHibernate();
        UserDao userDao = new UserDaoHibernate();

        List<Project> projectsList = projectDao.getAllProjects();
        List<User> usersList = userDao.getAllUsers();

        req.setAttribute(Constants.STATUSES_FILE, onCreateStatuses);
        req.setAttribute(Constants.TYPES_FILE, types);
        req.setAttribute(Constants.PRIORITIES_FILE, priorities);
        req.setAttribute(Constants.PROJECTS_LIST, projectsList);
        req.setAttribute(Constants.USERS_LIST, usersList);

        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.ADD_ISSUE_PAGE);
        dispatcher.forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
