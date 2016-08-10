package litvin.controllers.project.issues;

import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;
import litvin.constants.Notifications;
import litvin.controllers.AbstractServlet;
import litvin.dao.issue.IssueDao;
import litvin.dao.issue.IssueDaoHibernate;
import litvin.dao.issue.attachment.AttachmentDao;
import litvin.dao.issue.attachment.AttachmentDaoHibernate;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;
import litvin.dao.issue.comment.CommentDao;
import litvin.dao.issue.comment.CommentDaoHibernate;
import litvin.dao.project.ProjectDao;
import litvin.dao.project.ProjectDaoHibernate;
import litvin.dao.user.UserDao;
import litvin.dao.user.UserDaoHibernate;
import litvin.model.project.Attachment;
import litvin.model.project.Comment;
import litvin.model.project.Issue;
import litvin.model.project.Project;
import litvin.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@WebServlet(name = "EditIssueServlet", urlPatterns = "/operations/editIssue")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class EditIssueServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart(Constants.ISSUE_ATTACHMENT);
        String id = req.getParameter(Constants.ID);
        String summary = req.getParameter(Constants.ISSUE_SUMMARY);
        String description = req.getParameter(Constants.ISSUE_DESCRIPTION);
        String status = req.getParameter(Constants.ISSUE_STATUS);
        String resolution = req.getParameter(Constants.ISSUE_RESOLUTION);
        String type = req.getParameter(Constants.ISSUE_TYPE);
        String priority = req.getParameter(Constants.ISSUE_PRIORITY);
        String projectId = req.getParameter(Constants.ISSUE_PROJECT);
        String assigneeEmail = req.getParameter(Constants.ASSIGNEE_EMAIL);
        String modifierEmail = req.getParameter(Constants.MODIFIER_EMAIL);
        String commentText = req.getParameter(Constants.ISSUE_COMMENT);

        if (isEmptyOrNull(summary, description, status, type, priority, priority, projectId, assigneeEmail, modifierEmail)){
            jumpError(ConstError.EMPTY_LINE, ConstAddress.EDIT_ISSUE_SERVLET + id, req, resp);
            return;
        }
        String validationResult = validate(resolution, status, assigneeEmail);
        if (!validationResult.equals(ConstError.OK)){
            jumpError(validationResult, ConstAddress.EDIT_ISSUE_SERVLET + id, req, resp);
            return;
        }

        IssueDao issueDao = new IssueDaoHibernate();
        ProjectDao projectDao = new ProjectDaoHibernate();
        UserDao userDao = new UserDaoHibernate();
        CommentDao commentDao = new CommentDaoHibernate();
        AttachmentDao attachmentDao = new AttachmentDaoHibernate();

        Issue issue = issueDao.findIssueById(Integer.parseInt(id));
        Project project = projectDao.findProjectById(Integer.parseInt(projectId));
        User assignee = assigneeEmail.equals(Constants.USER_NOT_ASSIGNED)? null: userDao.getUser(assigneeEmail);
        User modifier = userDao.getUser(modifierEmail);

        issue.setSummary(summary);
        issue.setDescription(description);
        issue.setStatus(status);
        issue.setResolution(resolution);
        issue.setType(type);
        issue.setPriority(priority);
        issue.setProject(project);
        issue.setAssignee(assignee);
        issue.setModifier(modifier);

        if (commentText != null && !commentText.isEmpty()){
            Comment comment = new Comment();
            comment.setUser(modifier);
            comment.setDate(new Date());
            comment.setText(commentText);
            comment.setIssue(issue);
            commentDao.saveComment(comment);
            issue.getComments().add(comment);
        }

        if (part.getSize() != 0){
            InputStream inputStream = part.getInputStream();
            byte[] attachmentBytes = new byte[(int) part.getSize()];
            inputStream.read(attachmentBytes);
            inputStream.close();

            Attachment attachment = new Attachment();
            attachment.setFileName(getFileName(part));
            attachment.setUser(modifier);
            attachment.setFile(attachmentBytes);
            attachment.setIssue(issue);
            attachmentDao.saveAttachment(attachment);

            issue.getAttachments().add(attachment);
        }

        HttpSession session = req.getSession();
        session.setAttribute(Notifications.SUCCESS_MESSAGE, Notifications.ISSUE_EDITED);
        issueDao.updateIssue(issue);
        resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
    }

    private String validate(String resolution, String status, String assigneeEmail){
        final int NEW_ISSUE_INDEX = 0, RESOLVED_INDEX = 3, CLOSED_INDEX = 4;
        AttrDao attrDao = new AttrDaoFile();
        String[] statuses = attrDao.getAllAttributes(Constants.STATUSES_FILE);

        if (resolution != null){
            if (!status.equals(statuses[RESOLVED_INDEX]) && !status.equals(statuses[CLOSED_INDEX])){
                return ConstError.RESOLUTION_ERROR;
            }
        }
        if (status.equals(statuses[NEW_ISSUE_INDEX]) && !assigneeEmail.equals(Constants.USER_NOT_ASSIGNED)){
            return ConstError.NEW_STATUS_ERROR;
        }
        if (!status.equals(statuses[NEW_ISSUE_INDEX]) && assigneeEmail.equals(Constants.USER_NOT_ASSIGNED)){
            return ConstError.ASSIGNED_STATUS_ERROR;
        }
        return ConstError.OK;
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

}
