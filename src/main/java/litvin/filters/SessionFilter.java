package litvin.filters;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.model.user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            if (user == null) {
                session.invalidate();
                HttpServletResponse Response = (HttpServletResponse) servletResponse;
                Response.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

}
