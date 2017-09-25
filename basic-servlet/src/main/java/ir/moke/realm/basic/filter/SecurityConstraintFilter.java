package ir.moke.realm.basic.filter;

import org.apache.catalina.realm.GenericPrincipal;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class SecurityConstraintFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        GenericPrincipal genericPrincipal = (GenericPrincipal) httpServletRequest.getUserPrincipal();
        String uri = httpServletRequest.getRequestURI();
        if (uri.equals("/login")) {
            chain.doFilter(request, response);
        } else if (genericPrincipal == null) {
            httpServletRequest.getRequestDispatcher("/").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
