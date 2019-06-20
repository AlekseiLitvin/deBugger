package litvin.controllers;


import litvin.constants.ConstAddress;
import litvin.constants.ConstError;
import litvin.constants.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AbstractServlet extends HttpServlet {

    protected void jumpError(String cause, String url, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        req.setAttribute(Constants.ERROR_MESSAGE, cause);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jumpError(ConstError.METHOD_GET_ERROR, ConstAddress.MAIN_PAGE_SERVLET, req, resp);
    }

    protected boolean isEmptyOrNull(String... args) {
        for (String s : args) {
            if (s == null || s.isEmpty() || s.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
