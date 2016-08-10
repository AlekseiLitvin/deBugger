package litvin.controllers.project.issues.attributes;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
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

@WebServlet(name = "EditIAttrPageInitServlet", urlPatterns = "/admin/editAttribute/*")
public class EditIAttrPageInitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String DELIMITER = "-", FILE_NAME = "fileName", ATTRIBUTE_NAME = "oldAttributeName";
        final int ATTRIBUTE_TYPE_INDEX = 0, ATTRIBUTE_NAME_INDEX = 1;
        String pathVariable = ServletUtil.getPathVariable(req);
        String[] pathParts = pathVariable.split(DELIMITER);
        String fileName = pathParts[ATTRIBUTE_TYPE_INDEX];
        String attributeName = pathParts[ATTRIBUTE_NAME_INDEX];

        req.setAttribute(FILE_NAME, fileName);
        req.setAttribute(ATTRIBUTE_NAME, attributeName);
        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.ADD_ISSUE_ATTR_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
