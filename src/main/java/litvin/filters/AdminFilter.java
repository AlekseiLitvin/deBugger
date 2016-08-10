package litvin.filters;

import litvin.constants.ConstAddress;
import litvin.constants.Constants;
import litvin.model.user.Role;
import litvin.model.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.USER);
        if (!user.getRole().equals(Role.ADMIN.name())){
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(ConstAddress.MAIN_PAGE_SERVLET);
        }else {
            chain.doFilter(request, response);
        }
    }

}
