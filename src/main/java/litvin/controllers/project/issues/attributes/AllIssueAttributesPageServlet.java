package litvin.controllers.project.issues.attributes;

import litvin.constants.ConstAddress;
import litvin.controllers.ServletUtil;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AllIssueAttributesPageServlet", urlPatterns = "/admin/allIssueAttributes/*")
public class AllIssueAttributesPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String ATTRIBUTES_ARRAY = "attributesArray", ATTRIBUTE_NAME = "attributeName";

        String attributeName = ServletUtil.getPathVariable(req);
        AttrDao attrDao = new AttrDaoFile();
        String[] attributesArray = attrDao.getAllAttributes(attributeName);

        req.setAttribute(ATTRIBUTE_NAME, attributeName);
        req.setAttribute(ATTRIBUTES_ARRAY, attributesArray);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.ALL_ISSUE_ATTRIBUTES_PAGE);
        dispatcher.forward(req, resp);
    }

}
