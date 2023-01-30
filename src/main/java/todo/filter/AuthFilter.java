package todo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class AuthFilter implements Filter {

    private static final Set<String> TEMPLATES_SET = Set.of("index", "login", "loginPage", "fail", "success",
            "registrationFailed", "registrationSuccess", "addUser", "registration");

    private boolean checkSet(String uri) {
        return TEMPLATES_SET.stream().anyMatch(uri::endsWith);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (checkSet(uri)) {
            filterChain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/users/loginPage");
            return;
        }
        filterChain.doFilter(req, res);
    }
}
