package litvin.controllers.project.issues;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.model.project.Issue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AllIssuesServlet", urlPatterns = "/operations/allIssues")
public class AllIssuesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IssueDao issueDao = new IssueDaoHibernate();
        List<Issue> issuesList = issueDao.getAllIssues();

        req.setAttribute(Constants.ISSUES_LIST, issuesList);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.MAIN_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
