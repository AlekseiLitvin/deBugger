package litvin.controllers.project;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.constants.Notifications;
import litvin.controllers.AbstractServlet;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.exceptions.DaoException;
import litvin.model.project.Project;
import litvin.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addProjectServlet", urlPatterns = "/admin/addProject")
public class AddProjectServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter(Constants.PROJECT_ID);
        String name = req.getParameter(Constants.PROJECT_NAME);
        String description = req.getParameter(Constants.PROJECT_DESCRIPTION);
        String build = req.getParameter(Constants.PROJECT_BUILD);
        String managerEmail = req.getParameter(Constants.PROJECT_MANAGER_EMAIL);

        if (isEmptyOrNull(name, description, build, managerEmail)) {
            jumpError(ConstError.EMPTY_EMAIL_OR_PASSWORD, ConstAddress.PROJECT_PAGE_INIT_SERVLET, req, resp);
            return;
        }
        UserDao userDao = new UserDaoHibernate();
        try {
            User manager = userDao.getUser(managerEmail);
            if (manager == null) {
                jumpError(ConstError.WRONG_EMAIL, ConstAddress.PROJECT_PAGE_INIT_SERVLET, req, resp);
                return;
            }
            ProjectDao projectDao = new ProjectDaoHibernate();
            HttpSession session = req.getSession();
            if (projectId != null && !projectId.isEmpty()) {
                Project project = projectDao.findProjectById(Integer.parseInt(projectId));
                project.setName(name);
                project.setDescription(description);
                project.setBuild(build);
                project.setManager(manager);

                session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.PROJECT_EDITED);
                projectDao.updateProject(project);
            } else {
                Project project = new Project();
                project.setName(name);
                project.setDescription(description);
                project.setBuild(build);
                project.setManager(manager);

                session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.PROJECT_ADDED);
                projectDao.saveProject(project);
            }

            resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
        } catch (DaoException e) {
            jumpError(ConstError.DAO_ERROR, ConstAddress.PROJECT_PAGE_INIT_SERVLET, req, resp);
        }
    }

}
