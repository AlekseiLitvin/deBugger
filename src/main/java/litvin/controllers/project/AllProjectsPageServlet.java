package litvin.controllers.project;

import litvin.constants.ConstAddress;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.model.project.Project;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllProjectsPageServlet", urlPatterns = "/admin/allProjects")
public class AllProjectsPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String PROJECTS_LIST_ATTR = "projectsList";

        ProjectDao projectDao = new ProjectDaoHibernate();
        List<Project> projectsList = projectDao.getAllProjects();

        req.setAttribute(PROJECTS_LIST_ATTR, projectsList);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.ALL_PROJECTS_PAGE);
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
