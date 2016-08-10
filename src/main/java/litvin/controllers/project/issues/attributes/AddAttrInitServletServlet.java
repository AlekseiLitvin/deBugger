package litvin.controllers.project.issues.attributes;

import litvin.constants.ConstAddress;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAttrInitServletServlet", urlPatterns ="/admin/addIssueAttr/*")
public class AddAttrInitServletServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String FILE_NAME = "fileName", DELIMITER = "/";
        final int QUERY_INDEX = 1;
        String path = req.getPathInfo();
        String[] pathParts = path.split(DELIMITER);
        String fileName = pathParts[QUERY_INDEX];

        req.setAttribute(FILE_NAME, fileName);

        RequestDispatcher dispatcher = req.getRequestDispatcher(ConstAddress.ADD_ISSUE_ATTR_PAGE);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
