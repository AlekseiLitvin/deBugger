package litvin.controllers.project.issues;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.controllers.ServletUtil;
import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.project.Issue;
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

@WebServlet(name = "EditIssuePageInitServlet", urlPatterns = "/editIssuePage/*")
public class EditIssuePageInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String ISSUE = "issue", PROJECTS_LIST = "projectsList";
        String issueId = ServletUtil.getPathVariable(req);

        IssueDao issueDao = new IssueDaoHibernate();
        ProjectDao projectDao = new ProjectDaoHibernate();
        AttrDao attrDao = new AttrDaoFile();
        UserDao userDao = new UserDaoHibernate();

        Issue issue = issueDao.findIssueById(Integer.parseInt(issueId));
        List<Project> projectsList = projectDao.getAllProjects();
        String[] resolutions = attrDao.getAllAttributes(Constants.RESOLUTIONS_FILE);
        String[] types = attrDao.getAllAttributes(Constants.TYPES_FILE);
        String[] priorities = attrDao.getAllAttributes(Constants.PRIORITIES_FILE);
        String[] statuses = attrDao.getAllAttributes(Constants.STATUSES_FILE);

        List<User> usersList = userDao.getAllUsers();

        req.setAttribute(Constants.USERS_LIST, usersList);
        req.setAttribute(Constants.RESOLUTIONS_FILE, resolutions);
        req.setAttribute(Constants.TYPES_FILE, types);
        req.setAttribute(Constants.PRIORITIES_FILE, priorities);
        req.setAttribute(Constants.STATUSES_FILE, statuses);
        req.setAttribute(PROJECTS_LIST, projectsList);
        req.setAttribute(ISSUE ,issue);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.EDIT_ISSUE_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
