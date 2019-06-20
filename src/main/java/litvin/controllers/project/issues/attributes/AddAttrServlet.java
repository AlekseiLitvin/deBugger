package litvin.controllers.project.issues.attributes;

import litvin.constants.ConstAddress;
import litvin.controllers.AbstractServlet;
import litvin.controllers.ServletUtil;
import litvin.dao.issue.attributes.AttrDao;
import litvin.dao.issue.attributes.AttrDaoFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAttrServlet", urlPatterns = "/admin/addAttribute/*")
public class AddAttrServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String NEW_ATTRIBUTE_NAME = "newAttributeName", OLD_ATTRIBUTE_NAME = "oldAttributeName";

        String fileName = ServletUtil.getPathVariable(req);
        String oldAttribute = req.getParameter(OLD_ATTRIBUTE_NAME);
        String newAttribute = req.getParameter(NEW_ATTRIBUTE_NAME);

        AttrDao attrDao = new AttrDaoFile();
        if (oldAttribute != null && !oldAttribute.isEmpty()) {
            attrDao.editAttribute(fileName, oldAttribute, newAttribute);
        } else {
            attrDao.addAttribute(fileName, newAttribute);
        }
        resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
    }
}
