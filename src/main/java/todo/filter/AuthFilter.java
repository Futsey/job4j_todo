package todo.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class AuthFilter extends HttpFilter {

    private static final Set<String> TEMPLATES_SET = Set.of("index", "formInfo", "formUpdate",
            "edit", "execution", "formAdd", "create", "delete");

    private boolean checkSet(String uri) {
        return TEMPLATES_SET.stream().anyMatch(uri::endsWith);
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (checkSet(uri)) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("users") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, res);
    }
}
