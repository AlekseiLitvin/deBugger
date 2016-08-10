package litvin.controllers.project.issues;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.constants.Notifications;
import litvin.controllers.AbstractServlet;
import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.exceptions.DaoException;
import litvin.model.project.Issue;
import litvin.model.project.Project;
import litvin.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "AddIssueServlet", urlPatterns = "/operations/addIssue")
public class AddIssueServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String summary = req.getParameter(Constants.ISSUE_SUMMARY);
        String description = req.getParameter(Constants.ISSUE_DESCRIPTION);
        String status = req.getParameter(Constants.ISSUE_STATUS);
        String type = req.getParameter(Constants.ISSUE_TYPE);
        String priority = req.getParameter(Constants.ISSUE_PRIORITY);
        String projectId = req.getParameter(Constants.ISSUE_PROJECT);
        String assigneeEmail = req.getParameter(Constants.ASSIGNEE_EMAIL);
        String modifierEmail = req.getParameter(Constants.MODIFIER_EMAIL);

        if (isEmptyOrNull(summary, description, status, type, priority, projectId, assigneeEmail)){
            jumpError(ConstError.EMPTY_LINE, ConstAddress.ISSUE_PAGE_INIT_SERVLET, req, resp);
            return;
        }
        String validationResult = validate(status, assigneeEmail);
        if (!validationResult.equals(ConstError.OK)){
            jumpError(validationResult, ConstAddress.ISSUE_PAGE_INIT_SERVLET, req, resp);
            return;
        }


        try {
            ProjectDao projectDao = new ProjectDaoHibernate();
            UserDao userDao = new UserDaoHibernate();
            IssueDao issueDao = new IssueDaoHibernate();

            int projectIdDigit = Integer.parseInt(projectId);
            Project project = projectDao.findProjectById(projectIdDigit);
            User assignee = assigneeEmail.equals(Constants.USER_NOT_ASSIGNED)? null: userDao.getUser(assigneeEmail);
            User modifier = userDao.getUser(modifierEmail);

            Issue issue = new Issue();
            issue.setSummary(summary);
            issue.setDescription(description);
            issue.setStatus(status);
            issue.setType(type);
            issue.setPriority(priority);
            issue.setProject(project);
            issue.setBuild(project.getBuild());
            issue.setAssignee(assignee);
            issue.setCreator(modifier);
            issue.setCreateDate(new Date());
            issue.setModifier(modifier);
            issue.setModifyDate(new Date());

            issueDao.saveIssue(issue);

            HttpSession session = req.getSession();
            session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.ISSUE_ADDED);
            resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
        }catch (DaoException e){
            e.printStackTrace();
            jumpError(ConstError.DAO_ERROR, ConstAddress.ISSUE_PAGE_INIT_SERVLET, req, resp);
        }
    }

    private String validate(String status, String assigneeEmail){
        final int NEW_ISSUE_INDEX = 0;
        AttrDao attrDao = new AttrDaoFile();
        String[] statuses = attrDao.getAllAttributes(Constants.STATUSES_FILE);

        if (status.equals(statuses[NEW_ISSUE_INDEX]) && !assigneeEmail.equals(Constants.USER_NOT_ASSIGNED)){
            return ConstError.NEW_STATUS_ERROR;
        }
        if (!status.equals(statuses[NEW_ISSUE_INDEX]) && assigneeEmail.equals(Constants.USER_NOT_ASSIGNED)){
            return ConstError.ASSIGNED_STATUS_ERROR;
        }
        return ConstError.OK;
    }
}
