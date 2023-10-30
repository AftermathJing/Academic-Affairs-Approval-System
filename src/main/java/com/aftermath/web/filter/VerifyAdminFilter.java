package com.aftermath.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "FilterVerifyAdmin", urlPatterns = {"/admin", "/admin/*"})
public class VerifyAdminFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext context = fConfig.getServletContext();
        context.log("VerifyAdminFilter initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("login");

        if (user != null && user.equals("admin")) {
            chain.doFilter(request, response);
        } else {
            req.setAttribute("login_msg", "身份错误，您无权访问！");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    public void destroy() {
        //close any resources here
    }
}

